#!/usr/bin/env python
import os
import re
import sys
from subprocess import Popen, PIPE
import shutil
import numpy as np

PROGRAM_NAME = r'Program*'
WINDOWS_PROGRAM_NAME = r'[a-zA-Z0-9]*.exe'
RESULTS_FILE = "results.txt"
WINDOWS = "nt"
RELEASE_FOLDER = "Debug/"
SHADERS = r'[a-zA-Z0-9]*.spv'

# Return codes
SUCCESS = 0;
INVALID_CODE_GENERATION = -5
RUN_OUT_OF_MEMORY = -6

# Runs the programs
def runPrograms(executables):
	results = open(RESULTS_FILE, "w")
	succeeded = []
	failed = []
	outOfMemory = []
	invalidCodeGeneration = []

	print "Running Tests"
	print "==================="

	# Iterate over programs and run them
	for i in range(len(executables)):
		runningMessage = "Running: " + executables[i] + " (" + str(i + 1) + "/" + str(len(executables)) + ")"
		print runningMessage + "  ",
		results.write(runningMessage + "\n")

		process = Popen(["./" + executables[i]], stdout=PIPE, stderr=PIPE)
		(stdout, stderr) = process.communicate()

		# Check return code
		if process.returncode == SUCCESS:
			resultMessage = "SUCCESS"
			succeeded.append(executables[i])
		elif process.returncode == INVALID_CODE_GENERATION:
			resultMessage = "INVALID"
			invalidCodeGeneration.append(executables[i])
		elif process.returncode == RUN_OUT_OF_MEMORY:
			resultMessage = "SUCCESS"
			outOfMemory.append(executables[i])
		else:
			resultMessage = "FAILURE"
			failed.append(executables[i])

		results.write(resultMessage + "\n\n")
		print resultMessage

		# Write stdout & stderr to file
		results.write("STDOUT:\n")
		results.write(stdout + "\n")
		results.write("STDERR:\n")
		results.write(stderr + "\n")

	print "===================\n"
	return (succeeded, failed, outOfMemory, invalidCodeGeneration)

# Prints the test summary
def printSummary(executables, succeeded, failed, outOfMemory, invalidCodeGeneration):
	# Calculate statistics
	numberOfExecutables = len(executables)
	prcntSucceed = float(len(succeeded))/numberOfExecutables * 100.0 if numberOfExecutables !=0 else 0
	prcntFailed = float(len(failed))/numberOfExecutables * 100.0 if numberOfExecutables !=0 else 0
	prcntOutOfMemory = float(len(outOfMemory))/numberOfExecutables * 100.0 if numberOfExecutables !=0 else 0
	prcntInvalidCodeGeneration = float(len(invalidCodeGeneration))/numberOfExecutables * 100.0 if numberOfExecutables !=0 else 0

	print "Test Summary"
	print "==================="
	print "Total tests: " + str(numberOfExecutables)
	print "Succeeded: " + str(len(succeeded)) + "/" + str(numberOfExecutables) + " (" + str(prcntSucceed) + "%)"
	print "Failed: " + str(len(failed)) + "/" + str(numberOfExecutables) + " (" + str(prcntFailed) + "%)"
	print "Out of Memory: " + str(len(outOfMemory)) + "/" + str(numberOfExecutables) + " (" + str(prcntOutOfMemory) + "%)"
	print "Invalid Code Generation: " + str(len(invalidCodeGeneration)) + "/" + str(numberOfExecutables) + " (" + str(prcntInvalidCodeGeneration) + "%)"
	print "===================\n"

	if len(failed) != 0:
		print "Failed Programs"
		print "==================="

		for program in failed:
			print str(program)

		print "===================\n"

def collectStatistics(values):
	stats = open("statistics.txt", "r")
	tagsCount = {}
	tagsTotal = {}
	tagsAlt = {}

	tagsTotalx = {}

	for line in stats:
		if line == "Start new entry\n":
			tempTagsCount = {}
			tempTagsTotal = {}
			tempTagsAlt = {}
			continue
		elif line == "End new entry\n":
			bad = False
			for (tag,count) in tempTagsCount.iteritems():
				if (count % 2) != 0:
					bad = True

			if not bad:
				for (tag,count) in tempTagsCount.iteritems():

					if tag in tagsCount:
						tagsCount[tag] += count
					else:
						tagsCount[tag] = count

				for (tag,total) in tempTagsTotal.iteritems():
					if tag in tagsTotal:
						tagsTotal[tag] += total
					else:
						tagsTotal[tag] = total


			continue

		contents = line.split(",")
		tag = contents[0]
		if tag in tempTagsCount:
			tempTagsCount[tag] += 1
		else:
			tempTagsCount[tag] = 1

		time = contents[1]
		if tag in tempTagsTotal:
			if tempTagsAlt[tag]:
				tempTagsTotal[tag] -= long(time)
				tempTagsAlt[tag] = False
			else:
				tempTagsTotal[tag] += long(time)
				tempTagsAlt[tag] = True
		else:
			tempTagsTotal[tag] = -long(time)
			tempTagsAlt[tag] = False

	for (tag,total) in tagsTotal.iteritems():
		if tag in values:
			values[tag].append(total)
		else:
			values[tag] = []
			values[tag].append(total)


def printStatistics(stats):
	print "Statistics"
	print "==================="
	for (tag,values) in stats.iteritems():
		nparray = np.array(values)
		print nparray
		print tag + ": Mean " + str(np.mean(nparray)) + "ns, ",
		print "Median " + str(np.median(nparray)) + "ns, ",
		print "s.d " + str(np.std(nparray)) + "ns"

	print "==================="


if __name__ == "__main__":
	# Get all files in directory
	files = []
	executables = []
	if os.name == WINDOWS:
		files = os.listdir(RELEASE_FOLDER)
		executables = [RELEASE_FOLDER + x for x in files if re.match(WINDOWS_PROGRAM_NAME, x)]

		# Check if Shaders were copied
		existingShaders = os.listdir(RELEASE_FOLDER)

		# If shaders were not copied previously
		if len(existingShaders) == 0:
			shaderFiles = os.listdir(".")
			shaders = [x for x in shaderFiles if re.match(SHADERS, x)]

			for i in range(len(shaders)):
				shutil.copy(shaders[i], RELEASE_FOLDER + shaders[i])

	else:
		files = os.listdir(".")
		executables = [x for x in files if re.match(PROGRAM_NAME, x)]

	times = int(sys.argv[1])
	values = {}
	for i in range(times):
		# Delete previous stats file
		try:
			os.remove("statistics.txt")
		except OSError:
			pass

		# Run programs
		(succeeded, failed, outOfMemory, invalidCodeGeneration) = runPrograms(executables)

		# Print Summary
		printSummary(executables, succeeded, failed, outOfMemory, invalidCodeGeneration)

		collectStatistics(values)

	printStatistics(values)

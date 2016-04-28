#!/usr/bin/env python
import os
import re
import sys
from subprocess import Popen, PIPE
from time import sleep

PROGRAM_NAME = r'Program*'
WINDOWS_PROGRAM_NAME = r'[a-zA-Z0-9]*.exe'
RESULTS_FILE = "results.txt"
WINDOWS = "nt"
RELEASE_FOLDER = "Release/"

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

		print "==================="


if __name__ == "__main__":
	# Get all files in directory
	files = []
	executables = []
	if os.name == WINDOWS:
		files = os.listdir(RELEASE_FOLDER)
		executables = [RELEASE_FOLDER + x for x in files if re.match(WINDOWS_PROGRAM_NAME, x)]
	else:
		files = os.listdir(".")
		executables = [x for x in files if re.match(PROGRAM_NAME, x)]

	# Run programs
	(succeeded, failed, outOfMemory, invalidCodeGeneration) = runPrograms(executables)

	# Print Summary
	printSummary(executables, succeeded, failed, outOfMemory, invalidCodeGeneration)

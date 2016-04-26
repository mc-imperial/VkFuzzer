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
DEBUG_FOLDER = "Debug/"

# Runs the programs
def runPrograms(executables):
	results = open(RESULTS_FILE, "w")
	succeeded = []
	failed = []

	print "Running Tests"
	print "==================="

	# Iterate over programs and run them
	for i in range(len(executables)):
		prefix = ""
		if os.name == WINDOWS:
			if os.path.isdir(RELEASE_FOLDER):
				prefix = RELEASE_FOLDER
			else:
				prefix = DEBUG_FOLDER

		runningMessage = "Running: " + prefix + executables[i] + " (" + str(i + 1) + "/" + str(len(executables)) + ")"
		print runningMessage + "  ",
		results.write(runningMessage + "\n")

		process = Popen(["./" + prefix + executables[i]], stdout=PIPE, stderr=PIPE)
		(stdout, stderr) = process.communicate()

		# Check return code
		resultMessage = ""
		if process.returncode == 0:
			resultMessage = "SUCCESS"
			succeeded.append(executables[i])
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
	return (succeeded, failed)

# Prints the test summary
def printSummary(executables, succeeded, failed):
	# Calculate statistics
	numberOfExecutables = len(executables)
	prcntSucceed = float(len(succeeded))/numberOfExecutables * 100.0 if numberOfExecutables !=0 else 0
	prcntFailed = float(len(failed))/numberOfExecutables * 100.0 if numberOfExecutables !=0 else 0

	print "Test Summary"
	print "==================="
	print "Total tests: " + str(numberOfExecutables)
	print "Succeeded: " + str(len(succeeded)) + "/" + str(numberOfExecutables) + " (" + str(prcntSucceed) + "%)"
	print "Failed: " + str(len(failed)) + "/" + str(numberOfExecutables) + " (" + str(prcntFailed) + "%)"
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
		folder = ""

		if os.path.isdir(RELEASE_FOLDER):
			folder = RELEASE_FOLDER
		else:
			folder = DEBUG_FOLDER

		files = os.listdir(folder)
		executables = [x for x in files if re.match(WINDOWS_PROGRAM_NAME, x)]
	else:
		files = os.listdir(".")
		executables = [x for x in files if re.match(PROGRAM_NAME, x)]

	# Run programs
	(succeeded, failed) = runPrograms(executables)

	# Print Summary
	printSummary(executables, succeeded, failed)

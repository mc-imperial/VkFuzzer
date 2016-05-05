#include "ExitCondition.hpp"

namespace fuzzer
{

ExitCondition::ExitCondition() : _isFinished(false), _mutex()
{
}

ExitCondition::~ExitCondition()
{
}

void ExitCondition::signalFinish()
{
	std::lock_guard<std::mutex> lockGuard(_mutex);
	_isFinished = true;
}

bool ExitCondition::isFinished()
{
	std::lock_guard<std::mutex> lockGuard(_mutex);
	return _isFinished;
}

} // namespace fuzzer
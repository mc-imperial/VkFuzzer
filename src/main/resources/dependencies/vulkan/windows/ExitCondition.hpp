#ifndef _FUZZER_EXIT_CONDITION_HPP_
#define _FUZZER_EXIT_CONDITION_HPP_

#include <mutex>
#include <memory>

namespace fuzzer
{

class ExitCondition
{
public:
	ExitCondition();
	~ExitCondition();

	void signalFinish();
	bool isFinished();
private:
	bool _isFinished;
	std::mutex _mutex;
};

typedef std::shared_ptr<ExitCondition> ExitConditionPtr;

} // namespace fuzzer

#endif // _FUZZER_EXIT_CONDITION_HPP_
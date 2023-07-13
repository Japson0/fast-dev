jobId=${taskId}_${jobName}
java -DcurrentJobId=$jobId -DpreJobId=${preJobId} -jar $*
echo "#{setValue(preJobId=${jobId})}"
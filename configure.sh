#!/bin/bash
rm run.sh
mvn clean package
STATUS=$?
if [ $STATUS -eq 0 ]; then
echo "Build Successful"
cat <<EOF >run.sh
#!/bin/bash
eval "mvn exec:java -Dexec.args=\"\$1 \$2\""
EOF
else
echo "Build Failed"
fi
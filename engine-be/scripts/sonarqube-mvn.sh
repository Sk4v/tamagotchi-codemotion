# Check for arguments
if [ "$#" -ne 2 ]; then
    echo "Usage: $0 <login> <password>"
    exit 1
fi

LOGIN=$1
PASSWORD=$2

mvn clean verify sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login="$LOGIN" -Dsonar.password="$PASSWORD"

mvn verify sonar:sonar 
'-Dsonar.host.url=http://localhost:9000' \ 
'-Dsonar.login=admin -Dsonar.password=Alessandro.01'
'-Dsonar.dependencyCheck.summarize=true' \
'-Dsonar.dependencyCheck.jsonReportPath=target/dependency-check-report.json' \
'-Dsonar.dependencyCheck.xmlReportPath=target/dependency-check-report.xml' \
'-Dsonar.dependencyCheck.htmlReportPath=target/dependency-check-report.html' \
'-Dsonar.login=sqa_123456789abcdef' \
'-Dsonar.host.url=http://localhost:9000'
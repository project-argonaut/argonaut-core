echo "Building plugin..."
mvn -T 4 clean install
STATUS=$?

if [ $STATUS -eq 0 ]; then
    echo "Build passed! Copying completed plugin..."
    cp target/argonaut-core.jar ../test-server/plugins/
    echo "Launching server..."
    cd test-server/
    java -jar server.jar
else
    echo "Build failed! Check maven logs!"
fi

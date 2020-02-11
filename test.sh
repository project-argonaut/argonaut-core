echo "Building plugin..."
mvn -T 4 -Dstyle.color=always clean install shade:shade
STATUS=$?

if [ $STATUS -eq 0 ]; then
    echo "Build passed! Copying completed plugin..."
    cp core/target/argonaut-core.jar test-server/plugins/
    echo "Launching server..."
    cd test-server/
    java -jar server.jar -nogui
else
    echo "Build failed! Check maven logs!"
fi
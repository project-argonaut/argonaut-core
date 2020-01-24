echo "Setting up workspace..."
echo "Configuring test server..."
mkdir test-server/
cd test-server/
echo "Downloading paper 1.15.2..."
wget -O server.jar https://papermc.io/api/v1/paper/1.15.2/71/download
echo "Agreeing to EULA..."
echo "eula=true" >> eula.txt
echo "Creating plugins folder..."
mkdir plugins/
cd plugins/
echo "Downloading ProtocolLib..."
wget https://github.com/dmulloy2/ProtocolLib/releases/download/4.5.0/ProtocolLib.jar
echo "All Done!"

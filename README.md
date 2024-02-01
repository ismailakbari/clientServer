# Client-Server project

This is a Java/Gradle project written with OpenJDK 21 and Gradle 8.5
This project has two modules: client module and server module

## Table of Contents

- [Features](#features)
- [Requirements](#requirements)
- [Getting Started](#getting-started)
- [How to run](#how-to-run)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Features

- Client App
- Server App

## Requirements

- You need to have java available on the target machine.

## Getting Started
Download the client app from the client module. It's located at client/out/artifacts/clientServer_client_jar/clientServer_client.jar
Download the server app from the server module. It's located at server/out/artifacts/clientServer_server_jar/clientServer_server.jar
You need a dir to watch, a client config file, and a server config file. config files should be Java Properties file (key=value pairs in each line)
client app watches the watchDir directory for changes (add, modify) in Property files the directory. It reads and filters the properties based on the provided REGEX. Then the filtered props gets relayed to the server (including the prop file name).
The server app listens to the port and put the properties received in the determined output directory in a file withe same name as the client file.

## How to run
1. First run the client app
    $java -jar clientServer.client.jar /path_to_config/<client_config_file>.properties
    see "client_config.properties" and "client_config2.properties" in the repo as example.
    you can run multiple instances of the client app (with the same or different config file). It uses multithreading.

2. Run the server app
   $java -jar clientServer.server.jar /path_to_config/<server_config_file>.properties
   see "server_config.properties" in the repo as an example.


## Contributing
Ismail Akbari
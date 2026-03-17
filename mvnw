#!/bin/sh
# Maven Wrapper script
set -euo pipefail

MAVEN_PROJECTBASEDIR="${MAVEN_BASEDIR:-$(cd "$(dirname "$0")" && pwd)}"
MAVEN_WRAPPER_JAR="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.jar"
MAVEN_WRAPPER_PROPERTIES="$MAVEN_PROJECTBASEDIR/.mvn/wrapper/maven-wrapper.properties"

if [ -r "$MAVEN_WRAPPER_PROPERTIES" ]; then
    MAVEN_URL=$(grep -E '^distributionUrl=' "$MAVEN_WRAPPER_PROPERTIES" | cut -d'=' -f2-)
fi

MAVEN_HOME="$HOME/.m2/wrapper/dists/apache-maven-3.9.9"

if [ ! -d "$MAVEN_HOME" ]; then
    echo "Downloading Maven 3.9.9..."
    mkdir -p "$MAVEN_HOME"
    curl -sL "$MAVEN_URL" -o "/tmp/maven.zip"
    unzip -qo "/tmp/maven.zip" -d "$HOME/.m2/wrapper/dists/"
    rm -f "/tmp/maven.zip"
fi

exec "$MAVEN_HOME/bin/mvn" "$@" -Dmaven.multiModuleProjectDirectory="$MAVEN_PROJECTBASEDIR"

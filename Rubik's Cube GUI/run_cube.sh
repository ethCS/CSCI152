#!/bin/bash
# Auto-run JOGL 3D Cube project from Terminal

# Resolve Java path (uses system's installed JDK)
JAVA_HOME=$(/usr/libexec/java_home)

# Run program with native access and correct library paths
"$JAVA_HOME/bin/java" \
  --enable-native-access=ALL-UNNAMED \
  -Dfile.encoding=UTF-8 \
  -Djava.library.path="$(pwd)/natives/macosx-universal" \
  -classpath "$(pwd)/out/production/3d_cube:$(pwd)/lib/jogamp-all-platforms/jar/jogl-all.jar:$(pwd)/lib/jogamp-all-platforms/jar/gluegen.jar:/Users/ethan/Downloads/lib/j3dcore.jar:/Users/ethan/Downloads/lib/vecmath.jar:/Users/ethan/Downloads/lib/j3dutils.jar" \
  ButtonInputCubes

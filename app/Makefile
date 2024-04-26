setup:
	./gradlew wrapper --gradle-version 8.7

clean:
	./gradlew clean

build:
	./gradlew clean build

install:
	./gradlew clean installDist

run-dist:
	./build/install/app/bin/app

run-debug:
	./gradlew clean installDist
	./build/install/app/bin/app ./src/main/resources/file1.json ./src/main/resources/file2.json

run:
	./gradlew run

lint:
	./gradlew checkstyleMain

check-updates:
	./gradlew dependencyUpdates

.PHONY: build
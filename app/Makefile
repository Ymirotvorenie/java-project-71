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

run-debug-yml:
	./gradlew clean installDist
	./build/install/app/bin/app ./src/test/resources/file1.yml ./src/test/resources/file2.yml

run-debug-json:
	./gradlew clean installDist
	./build/install/app/bin/app ./src/test/resources/file1.json ./src/test/resources/file2.json
run:
	./gradlew run

lint:
	./gradlew checkstyleMain

report:
	./gradlew jacocoTestReport

test:
	./gradlew test

check-updates:
	./gradlew dependencyUpdates

.PHONY: build
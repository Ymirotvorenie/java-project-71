setup:
	make -C app setup

clean:
	make -C app clean

build:
	make -C app build

install:
	make -C app install

run-dist:
	make -C app run-dist

run:
	make -C app run

lint:
	make -C app lint

check-updates:
	make -C app check-updates


.PHONY: build
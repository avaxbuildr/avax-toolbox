#!/bin/bash

# Create a new wallet

cd "$(dirname "$0")/../.."

gradle --warning-mode=none -Dorg.gradle.logging.level=quiet -q run

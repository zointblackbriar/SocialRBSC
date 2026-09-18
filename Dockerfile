# Use the official Node.js image as the base image
FROM node:16.19.1
LABEL authors="Orcun Oruc"

# Set a working directory inside the container
WORKDIR /app

# Install Ganache globally
RUN npm install -g ganache

# Expose the default Ganache port (change it if needed)
EXPOSE 8545
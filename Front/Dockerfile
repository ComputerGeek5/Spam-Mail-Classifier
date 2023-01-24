# Stage 1
FROM node:19.4.0-alpine AS node

WORKDIR /app

COPY package.json /app

RUN npm install --legacy-peer-deps

COPY . /app
RUN npm run build

# Stage 2
FROM nginx:1.23-alpine

EXPOSE 80

COPY --from=node /app/dist /usr/share/nginx/html
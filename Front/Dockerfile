# Stage 1
FROM node:19.4.0-alpine AS node

EXPOSE 4000

WORKDIR /app

COPY package.json /app

RUN npm install --legacy-peer-deps

COPY . /app
RUN npm run build

# Stage 2
FROM nginx:1.23-alpine
COPY --from=node /app/dist /usr/share/nginx/html
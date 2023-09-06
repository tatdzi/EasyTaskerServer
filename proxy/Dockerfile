FROM nginx:1.25.1-alpine

COPY ./nginx-config/nginx-custom.conf /etc/nginx/conf.d/default.conf
COPY ../web-client/build ./usr/share/nginx/html/
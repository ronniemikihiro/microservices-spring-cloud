FROM node:14 as builder_app

WORKDIR /usr/src/app
COPY package.json package-lock.json ./

RUN npm install
COPY . .

ARG PROFILE
ENV PROFILE $PROFILE
RUN echo "Environment: ${PROFILE}"
RUN npm run build-${PROFILE}
#RUN npm run build-docker

EXPOSE 80

FROM nginx:1.19-alpine
COPY --from=builder_app /usr/src/app/dist/web/ /usr/share/nginx/html
COPY --from=builder_app /usr/src/app/nginx.conf /etc/nginx/conf.d/default.conf
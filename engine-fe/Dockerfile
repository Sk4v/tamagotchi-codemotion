FROM --platform=$BUILDPLATFORM node:18.16.0-alpine AS development

RUN mkdir /project
WORKDIR /project

COPY . .

RUN npm install -g @vue/cli
RUN npm install

ENV HOST=0.0.0.0

CMD ["npm", "run", "serve"]



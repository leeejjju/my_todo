# 1단계: 빌드
FROM node:20 AS builder
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build

# 2단계: 실제 실행 (nginx 사용)
FROM nginx:alpine
COPY --from=builder /app/build /usr/share/nginx/html

# Nginx 설정 (선택)
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]

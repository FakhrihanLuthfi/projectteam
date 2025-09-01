import axios from "axios";

const api = axios.create({
  baseURL: "http://192.168.18.231:8080", // ganti sesuai backend kamu
  headers: {
    "Content-Type": "application/json",
  },
});

export default api;

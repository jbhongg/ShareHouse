import axios from "axios";

// axios 객체 생성
export default axios.create({
  baseURL: "http://59.27.106.177:8092/",
  headers: {
    "Content-type": "application/json",
  },
});

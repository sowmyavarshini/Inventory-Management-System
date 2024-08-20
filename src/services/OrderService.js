import axios from "axios";

const REST_API_BASE_URL1="http://localhost:8080/fetchOrderDetails";

const REST_API_BASE_URL2="http://localhost:8080/fetchOrderById";

const REST_API_BASE_URL3="http://localhost:8080/createOrderDetails";

const REST_API_BASE_URL4="http://localhost:8080/updateOrderDetails";

export const listOrders=()=>axios.get(REST_API_BASE_URL1);

export const fetchOrderById=(orderId)=>axios.get(`${REST_API_BASE_URL2}`, { params: { orderId } });

export const addOrder = (order) =>axios.post(REST_API_BASE_URL3, order);

export const updateOrder= (order) =>axios.post(REST_API_BASE_URL4, order);
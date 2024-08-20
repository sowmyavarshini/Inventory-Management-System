import axios from "axios";

const REST_API_BASE_URL="http://localhost:8080/fetchProducts";

const REST_API_BASE_URL1="http://localhost:8080/fetchBrands";

const REST_API_BASE_URL2="http://localhost:8080/fetchCategories";

const REST_API_BASE_URL3="http://localhost:8080/createProduct";

const REST_API_BASE_URL4="http://localhost:8080/checkProductName";

const REST_API_BASE_URL5="http://localhost:8080/checkBarCode";

const REST_API_BASE_URL6="http://localhost:8080/fetchProductById";

const REST_API_BASE_URL7="http://localhost:8080/updateProduct";

const REST_API_BASE_URL8="http://localhost:8080/findProductsByBrandName";

const REST_API_BASE_URL9="http://localhost:8080/findProductsByCategoryName";

const REST_API_BASE_URL10="http://localhost:8080/ generateCustomerOrderReport";

const REST_API_BASE_URL11="http://localhost:8080/generateSalesReport";

const REST_API_BASE_URL12="http://localhost:8080/login";

const REST_API_BASE_URL13="http://localhost:8080/createCustomer";

const REST_API_BASE_URL14="http://localhost:8080/checkUsername";

const REST_API_BASE_URL15="http://localhost:8080/checkEmail";

const REST_API_BASE_URL16="http://localhost:8080/checkCityName";

const REST_API_BASE_URL17="http://localhost:8080/checkStateName";

const REST_API_BASE_URL18="http://localhost:8080/checkCountryName";

export const listBrands=()=>axios.get(REST_API_BASE_URL1);

export const listCategories=()=>axios.get(REST_API_BASE_URL2);

export const addProduct = (product) =>axios.post(REST_API_BASE_URL3, product);

export const fetchProductById=(productId)=>axios.get(`${REST_API_BASE_URL6}`, { params: { productId } });

export const listProducts=()=>axios.get(REST_API_BASE_URL);

export const updateProduct= (product) =>axios.post(REST_API_BASE_URL7, product);


export const checkProductName = (productName) => 
    axios.get(`${REST_API_BASE_URL4}`, { params: { productName } });
  
export const checkBarCode = (barcode) => 
    axios.get(`${REST_API_BASE_URL5}`, { params: { barcode } });

export const fetchProductsByBrand=(brandName)=>axios.get(`${REST_API_BASE_URL8}`, { params: { brandName } });

export const fetchProductsByCategory=(categoryName)=>axios.get(`${REST_API_BASE_URL9}`, { params: { categoryName } });


export const generateCustomerReport=(customerId)=>axios.get(`${REST_API_BASE_URL10}`, { params: { customerId } });

export const salesReport=()=>axios.get(REST_API_BASE_URL11);

export const login=(username, userpassword)=>axios.get(`${REST_API_BASE_URL12}`, { params: { username, userpassword } });

export const register = (user) =>axios.post(REST_API_BASE_URL13, user);

export const checkUserName = (username) => 
    axios.get(`${REST_API_BASE_URL14}`, { params: { username } });
  
export const checkEmail = (email) => 
    axios.get(`${REST_API_BASE_URL15}`, { params: { email } });

export const checkCityName = (cityName) => 
    axios.get(`${REST_API_BASE_URL16}`, { params: { cityName } });

export const checkStateName = (stateName) => 
    axios.get(`${REST_API_BASE_URL17}`, { params: { stateName } });

export const checkCountryName = (countryName) => 
    axios.get(`${REST_API_BASE_URL18}`, { params: { countryName } });
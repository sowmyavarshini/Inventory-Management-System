import React from 'react'
import {useState, useEffect} from 'react'
import '../App.css'
import { addProduct, listBrands, listCategories, checkBarCode, checkProductName } from '../services/ProductService'

const AddProduct = () => {

  const[productName, setProduct] =useState('')
  const[stockAvailable, setStockAvailable] = useState('')
  const[price, setPrice]= useState('')
  const[barcode, setBarcode] = useState('')
  const [brandId, setBrandId] = useState('');
  const [categoryId, setCategoryId] = useState('');
  const [brands, setBrands] = useState([]);
  const [categories, setCategories] = useState([]);
  const [message, setMessage] = useState(''); 
  const [errors, setErrors] = useState({});
  const [productNameUnique, setProductNameUnique] = useState(true);
  const [barcodeUnique, setBarcodeUnique] = useState(true);


  const validate = () => {
    const errors = {};
    if (!productName) errors.productName = "Product name cannot be blank.";
    if (!stockAvailable) errors.stockAvailable = "Stock quantity cannot be null.";
    if (stockAvailable < 0) errors.stockAvailable = "Stock available must be greater than 0.";
    if (!price) errors.price = "Price cannot be null.";
    if (price <= 0) errors.price = "Price must be greater than 0.";
    if (!barcode) errors.barcode = "Bar code cannot be blank.";
    if (!/^[A-Z]{4}\d{4}$/.test(barcode)) {
      errors.barcode = "Bar code must start with alphabet followed by numbers. Bar code must be exactly 8 characters.";
    }
    if (!brandId) errors.brandId = "Brand name is mandatory.";
    if (!categoryId) errors.categoryId = "Category name is mandatory.";
    return errors;
  };

  useEffect(() => {
    const fetchBrandsAndCategories = async () => {
      try {
        const [brandsResponse, categoriesResponse] = await Promise.all([
          listBrands(),
          listCategories()
        ]);

        setBrands(brandsResponse.data);
        setCategories(categoriesResponse.data);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchBrandsAndCategories();
  }, []);

  const handleBlur = async (field) => {
    if (field === 'productName') {
      try {
        const response = await checkProductName(productName);
        setProductNameUnique(response.data);
        if (response.data) {
          setErrors(prevErrors => ({ ...prevErrors, productName: "Product name already exists." }));
        } else {
          setErrors(prevErrors => ({ ...prevErrors, productName: "" }));
        }
      } catch (error) {
        console.error('Error checking product name:', error);
      }
    } else if (field === 'barcode') {
      try {
        const response = await checkBarCode(barcode);
        setBarcodeUnique(response.data);
        if (response.data) {
          setErrors(prevErrors => ({ ...prevErrors, barcode: "Barcode already exists." }));
        } else {
          setErrors(prevErrors => ({ ...prevErrors, barcode: "" }));
        }
      } catch (error) {
        console.error('Error checking barcode:', error);
      }
    }
  };

  
  const saveProduct = async (e) => {
    e.preventDefault();
    const errors = validate();
    if (Object.keys(errors).length > 0) {
      setErrors(errors);
      return;
    }

    const product2 = { productName, stockAvailable, price, barcode, brandId, categoryId };
    console.log('Request Payload:', product2);

    try {
      const response = await addProduct(product2);
      setMessage(`Product successfully created with ID: ${response.data.productId}`);
      setErrors({});
    

    } catch (error) {
      console.error('Error:', error.message);
    }
  };

  return (
    <div className='container'>
      <div className='row'>
        <div className='card'>
          <h2 className='text-center mt-2'>Add Product</h2>
          <div className='card-body'>
            <form>
              <div className='row'>
              <div className='form-group mb-2'>
                <label className='form-label'>Enter the product name</label>
                <input 
                type='text'
                 placeholder="Product Name" 
                 name='productName' 
                 value={productName} 
                 className={`form-control ${errors.productName ? 'is-invalid' : ''}`}
                 onChange={(e)=>
                  setProduct(e.target.value)}
                  onBlur={() => handleBlur('productName')}></input>
                   {errors.productName && <p className="text-danger">{errors.productName}</p>}
              </div>

              <div className='form-group mb-2'>
                <label className='form-label'>Enter the stock available</label>
                <input 
                type='number'
                 placeholder="Stock Available" 
                 name='stockAvailable' 
                 value={stockAvailable} 
                 className={`form-control ${errors.stockAvailable ? 'is-invalid' : ''}`}
                 onChange={(e)=>
  setStockAvailable(e.target.value)}
                 min="1"
          
                 ></input>
                    {errors.stockAvailable && <p className="text-danger">{errors.stockAvailable}</p>}
              </div>

              <div className='form-group mb-2'>
                <label className='form-label'>Enter the product price</label>
                <input 
                type='number'
                 placeholder="Product Price" 
                 name='price' 
                 value={price} 
                 className={`form-control ${errors.price ? 'is-invalid' : ''}`}
                 onChange={(e)=>
                  setPrice(e.target.value)}
                ></input>
                   {errors.price && <p className="text-danger">{errors.price}</p>}
              </div>

              <div className='form-group mb-2'>
                <label className='form-label'>Enter the product barcode</label>
                <input 
                type='text'
                 placeholder="Bar Code" 
                 name='barcode' 
                 value={barcode} 
                 className={`form-control ${errors.barcode ? 'is-invalid' : ''}`}
                 onChange={(e)=>
                  setBarcode(e.target.value)}
                  onBlur={() => handleBlur('barcode')}></input>
                {errors.barcode && <p className="text-danger">{errors.barcode}</p>}
              </div>

              <div className='form-group mb-2'>
                <label className='form-label'>Select the product brand</label>
                <select
                  name='productBrand'
                  value={brandId}
                  className={`form-control select-custom ${errors.brandId ? 'is-invalid' : ''}`} 
                  onChange={(e)=>
                    setBrandId(e.target.value)}
                >
                  <option value=''>Select a brand</option>
                  {brands.map((brand) => (
                    <option key={brand.brandId} value={brand.brandId}>
                      {brand.brandName}
                    </option>
                  ))}
                </select>
                {errors.brandId && <p className="text-danger">{errors.brandId}</p>}
              </div>

              <div className='form-group mb-2'>
                <label className='form-label'>Select the product category</label>
                <select
                  name='productCategory'
                  value={categoryId}
                  className={`form-control select-custom ${errors.categoryId ? 'is-invalid' : ''}`}  // Apply the custom class here
                  onChange={(e)=>
                    setCategoryId(e.target.value)}
                >
                  <option value=''>Select a category</option>
                  {categories.map((category) => (
                    <option key={category.categoryId} value={category.categoryId}>
                      {category.categoryName}
                    </option>
                  ))}
                </select>
                {errors.categoryId && <p className="text-danger">{errors.categoryId}</p>}
              </div>

              <div className="submit-btn-container">
              <button className='submit-btn' onClick={saveProduct}>Add Product</button>
              </div>
              </div>
              {message && <p className="text-success mt-3">{message}</p>}
            </form>
          </div>
        </div>
      </div>
    </div>
  )
}

export default AddProduct
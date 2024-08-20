import React, { useEffect, useState } from 'react';
import { fetchProductById, updateProduct, listBrands, listCategories, checkProductName, checkBarCode } from '../services/ProductService';
import '../App.css';

const UpdateProduct = () => {
  const [productId, setProductId] = useState('');
  const [product, setProduct] = useState(null);
  const [originalBrandId, setOriginalBrandId] = useState('');
  const [originalCategoryId, setOriginalCategoryId] = useState('');
  const [brands, setBrands] = useState([]);
  const [categories, setCategories] = useState([]);
  const [message, setMessage] = useState('');
  const [errors, setErrors] = useState({});
  const [productNameUnique, setProductNameUnique] = useState(true);
  const [barcodeUnique, setBarcodeUnique] = useState(true);

  useEffect(() => {
    // Fetch brands and categories
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

  const validate = () => {
    const errors = {};
    if (!product.productName) errors.productName = "Product name cannot be blank.";
    if (!product.stockAvailable) errors.stockAvailable = "Stock quantity cannot be null.";
    if (product.stockAvailable === undefined || product.stockAvailable < 0) errors.stockAvailable = "Stock available must be greater than 0.";
    if (!product.price) {
      errors.price = "Price cannot be null.";
    } else if (product.price === undefined || product.price <= 0) {
      errors.price = "Price must be greater than 0.";
    }
    if (!product.barcode) errors.barcode = "Bar code cannot be blank.";
    if (!/^[A-Z]{4}\d{4}$/.test(product.barcode)) {
      errors.barcode = "Bar code must start with 4 uppercase letters followed by 4 digits.";
    }
    if (!product.brandId) errors.brand = "Brand name is mandatory.";
    if (!product.categoryId) errors.category = "Category name is mandatory.";
    return errors;
  };

  const handleBlur = async (field) => {
    if (field === 'productName') {
      try {
        const response = await checkProductName(product.productName);
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
        const response = await checkBarCode(product.barcode);
        setBarcodeUnique(response.data);
        console.log(response.data);
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

  const handleFetchProduct = async () => {
    try {
      const response = await fetchProductById(productId);
      setProduct(response.data);
      setOriginalBrandId(response.data.brandId);
      setOriginalCategoryId(response.data.categoryId);
      setErrors({});
    } catch (error) {
      console.error('Error fetching product:', error);
      setErrors({ fetch: 'Product not found.' });
    }
  };

  const handleUpdateProduct = async () => {
    const validationErrors = validate();
    if (Object.keys(validationErrors).length > 0) {
      setErrors(validationErrors);
      return;
    }

    try {
      const response = await updateProduct(product);
      setMessage(`Product successfully updated with ID: ${response.data.productId}`);
      setErrors({});
    } catch (error) {
      console.error('Error updating product:', error);
      setErrors({ update: 'Failed to update product.' });
    }
  };

  return (
    <div className="container">
      <div className='text-center'>
      <h2>Update Product</h2>

      {/* Step 1: Enter Product ID */}
      
        <label><h5>Enter Product ID:</h5></label>
        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center'}}>
        <input
          type="number"
          value={productId}
          className='form-control'
          onChange={(e) => setProductId(e.target.value)}
          min="1"
          style={{ width: '25%' }} 
        />
        </div>
         </div>
       <div className='mt-10'>
        <button className='submit-btn mb-3' onClick={handleFetchProduct}>Fetch Product</button>
        {errors.fetch && <p className="text-danger text-center">{errors.fetch}</p>}
        </div>
     

      {/* Step 2: Display and Edit Product Details */}
      {product && (
        <div className='row'>
        <div className="card">
        <h3 className='text-center mt-2'>Product Details</h3>
        <div className='card-body'>
        <div className='row'>
        <div className='form-group mb-2'>
      
            <label className='form-label'>Enter the product name</label>
            <input
              type="text"
              value={product.productName}
              className={`form-control ${errors.productName ? 'is-invalid' : ''}`}
              onChange={(e) => setProduct({ ...product, productName: e.target.value })}
              onBlur={() => handleBlur('productName')}
            />
            {errors.productName && <p className="text-danger">{errors.productName}</p>}
          </div>

          
          <div className='form-group mb-2'>
            <label className='form-label'>Enter the stock available</label>
            <input
              type="number"
              min="1"
              value={product.stockAvailable}
              className={`form-control ${errors.stockAvailable ? 'is-invalid' : ''}`}
              onChange={(e) => setProduct({ ...product, stockAvailable: e.target.value })}
            />
            {errors.stockAvailable && <p className="text-danger">{errors.stockAvailable}</p>}
          </div>

          <div className='form-group mb-2'>
          <label className='form-label'>Enter the product price</label>
            <input
              type="number"
              value={product.price}
              min="1"
              className={`form-control ${errors.price ? 'is-invalid' : ''}`}
              onChange={(e) => setProduct({ ...product, price: e.target.value })}
            />
            {errors.price && <p className="text-danger">{errors.price}</p>}
          </div>

          <div className='form-group mb-2'>
                  <label className='form-label'>Enter the product barcode</label>
            <input
              type="text"
              value={product.barcode}
              className={`form-control ${errors.barcode ? 'is-invalid' : ''}`}
              onChange={(e) => setProduct({ ...product, barcode: e.target.value })}
              onBlur={() => handleBlur('barcode')}
            />
            {errors.barcode && <p className="text-danger">{errors.barcode}</p>}
          </div>

          <div className='form-group mb-2'>
                  <label className='form-label'>Select the product brand</label>
            <select
              value={product.brandId}
              className={`form-control select-custom ${errors.brandId ? 'is-invalid' : ''}`} 
              onChange={(e) => setProduct({ ...product, brandId: e.target.value })}
            >
              <option value=''>Select a brand</option>
              {brands.map((brand) => (
                <option key={brand.brandId} value={brand.brandId}>
                  {brand.brandName}
                </option>
              ))}
            </select>
            {errors.brand && <p className="text-danger">{errors.brand}</p>}
          </div>

          
          <div className='form-group mb-2'>
                  <label className='form-label'>Select the product category</label>
            <select
              value={product.categoryId}
              className={`form-control select-custom ${errors.categoryId ? 'is-invalid' : ''}`}
              onChange={(e) => setProduct({ ...product, categoryId: e.target.value })}
            >
              <option value=''>Select a category</option>
              {categories.map((category) => (
                <option key={category.categoryId} value={category.categoryId}>
                  {category.categoryName}
                </option>
              ))}
            </select>
            {errors.category && <p className="text-danger">{errors.category}</p>}
          </div>
          <div className="submit-btn-container">
          <button className='submit-btn' onClick={handleUpdateProduct}>Update Product</button>
          </div>
          {errors.update && <p className="text-danger">{errors.update}</p>}
        </div>
        </div>
        </div>
        </div>
      )}

      {/* Step 3: Show Confirmation Message */}
      {message && <p className="text-success">{message}</p>}
    </div>
    
   
  );
};

export default UpdateProduct;

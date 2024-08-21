import React, { useState, useEffect } from 'react';
import { addOrder} from '../services/OrderService'; 
import { listProducts } from '../services/ProductService'; 
import '../App.css';

const AddOrder = () => {
  const [orderedQuantity, setOrderedQuantity] = useState('');
  const [selectedProduct, setSelectedProduct] = useState('');
  const [products, setProducts] = useState([]);
  const [quantityError, setQuantityError] = useState('');
  const [productError, setProductError] = useState('');
  const [message, setMessage] = useState('');
  const [errors, setErrors] = useState('');

  useEffect(() => {
    // Fetch the list of products to populate the dropdown
    const fetchProducts = async () => {
      try {
        const response = await listProducts();
        setProducts(response.data);
      } catch (error) {
        console.error('Error fetching products:', error);
      }
    };

    fetchProducts();
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();

    setQuantityError('');
    setProductError('');

    // Basic validation

    let hasError = false;
    if (!orderedQuantity || orderedQuantity < 1) {
      setQuantityError('Ordered quantity must be at least 1.');
      hasError = true;
    }
    if (!selectedProduct) {
      setProductError('Please select a product.');
      hasError = true;
    }
    if (hasError) return;
    try {
      const orderData = {
        orderedQuantity,
        productId: selectedProduct,
      };


      const response=await addOrder(orderData);
      setMessage(`Order successfully created with ID: ${response.data.orderId}`);
      setErrors('');
      setOrderedQuantity('');
      setSelectedProduct('');
    } catch (error) {
      console.error('Error creating order:', error);
      setMessage('');
      setErrors('Failed to create order.');
    }
  };

  return (
    <div className="container">
      <div className='row'>
      <div className="card">
        <h3 className="text-center mt-2">Create Order</h3>
        <div className="card-body">
          <form onSubmit={handleSubmit}>
            <div className='row'>
            <div className="form-group mb-2">
              <label className='form-label'>Enter the Order Quantity</label>
              <input
                type="number"
                className={`form-control ${quantityError ? 'is-invalid' : ''}`}
                value={orderedQuantity}
                onChange={(e) => setOrderedQuantity(e.target.value)}
                placeholder="Ordered quantity"
                min="1"
              />
               {quantityError && <p className="text-danger mt-1">{quantityError}</p>}
            </div>
            <div className="form-group mb-2">
              <label className='form-label'>Select a Product</label>
              <select
                className={`form-control ${productError ? 'is-invalid' : ''}`}
                value={selectedProduct}
                onChange={(e) => setSelectedProduct(e.target.value)}
              >
                <option value="">Select a product</option>
                {products.map((product) => (
                  <option key={product.productId} value={product.productId}>
                    {product.productName}
                  </option>
                ))}
              </select>
              {productError && <p className="text-danger mt-1">{productError}</p>}
            </div>
    
            <div className="submit-btn-container">
              <button type="submit" className="submit-btn">
                Create Order
              </button>
            </div>
            </div>
            {message && <p className="text-success mt-2">{message}</p>}
          </form>
        </div>
        </div>
      </div>
    </div>
  );
};

export default AddOrder;

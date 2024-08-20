import React, { useState, useEffect } from 'react';
import { fetchOrderById, updateOrder } from '../services/OrderService';
import { listProducts } from '../services/ProductService'; 
import '../App.css';

const UpdateOrder = () => {
  const [orderId, setOrderId] = useState('');
  const [orderDetails, setOrderDetails] = useState(null);
  const [orderedQuantity, setOrderedQuantity] = useState('');
  const [selectedProductId, setSelectedProductId] = useState('');
  const [products, setProducts] = useState([]);
  const [quantityError, setQuantityError] = useState('');
  const [productError, setProductError] = useState('');
  const [fetchError, setFetchError] = useState('');
  const [updateMessage, setUpdateMessage] = useState('');

  useEffect(() => {
    const loadProducts = async () => {
      try {
        const response = await listProducts();
        console.log('Fetched products:', response.data); // Log the response data
        setProducts(response.data);
      } catch (error) {
        console.error('Error fetching products:', error);
      }
    };
    loadProducts();
  }, []);

  const fetchOrder = async () => {
    if (orderId.trim() === '') {
      setFetchError('Order ID cannot be blank.');
      return;
    }

    try {
      const response = await fetchOrderById(orderId);
      const { orderedQuantity, productId } = response.data;
      setOrderDetails(response.data);
      setOrderedQuantity(orderedQuantity);
      setSelectedProductId(productId);
      setFetchError('');
      setQuantityError('');
      setProductError('');
    } catch (error) {
      console.error('Error fetching order:', error);
      setOrderDetails(null);
      setFetchError('Failed to fetch order.');
    }
  };

  const handleUpdateOrder = async () => {
    let isValid = true;

    if (!orderedQuantity || orderedQuantity < 1) {
      setQuantityError('Ordered quantity must be at least 1.');
      isValid = false;
    } else {
      setQuantityError('');
    }

    if (!selectedProductId) {
      setProductError('Please select a product.');
      isValid = false;
    } else {
      setProductError('');
    }

    if (!isValid) return;

    try {
      const updatedOrder = {
        orderId,
        orderedQuantity,
        productId: selectedProductId,
      };
      await updateOrder(updatedOrder);
      setUpdateMessage(`Order updated successfully for ID: ${orderId}`);
    } catch (error) {
      console.error('Error updating order:', error);
      setUpdateMessage('Failed to update order.');
    }
  };

  return (
    <div className="fetch-container">
      <h2 className="text-center">Update Order</h2>

      {/* Step 1: Enter Order ID */}
      <div className="text-center">
        <label><h5>Enter Order ID:</h5></label>
        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center'}}>
        <input
          type="number"
          value={orderId}
          className="form-control"
          onChange={(e) => setOrderId(e.target.value)}
          style={{ width: '30%' }} 
         
        />
        </div>
        </div>
        <div className='mt-10'>
        <button className="submit-btn mb-3" onClick={fetchOrder}>
          Fetch Order
        </button>
      
        {fetchError && <p className="text-danger mt-2 text-center">{fetchError}</p>}
      </div>

      {/* Step 2: Display Order Details for Editing */}
      {orderDetails && (
        <div className='row'>
        <div className="card">
        <h3 className='text-center mt-2'>Order Details</h3>
        <div className='card-body'>
        <div className='row'>
        <div className='form-group mb-2'>

      
            <label className='form-label'>Enter the Order Quantity:</label>
            <input
              type="number"
              className="form-control"
              value={orderedQuantity}
              onChange={(e) => setOrderedQuantity(e.target.value)}
              min="1"
            />
            {quantityError && <p className="text-danger mt-1">{quantityError}</p>}
          </div>

          <div className="form-group mb-2">
            <label className='form-label'>Select a product</label>
            <select
              className="form-control"
              value={selectedProductId}
              onChange={(e) => setSelectedProductId(e.target.value)}
            >
              <option value="">Select a product</option>
              {products.length === 0 ? (
                <option value="" disabled>No products available</option>
              ) : (
                products.map((product) => (
                  <option key={product.productId} value={product.productId}>
                    {product.productName}
                  </option>
                ))
              )}
            </select>
            {productError && <p className="text-danger mt-1">{productError}</p>}
          </div>
          <div className="submit-btn-container">
          <button className='submit-btn'onClick={handleUpdateOrder}>
            Update Order
          </button>
         </div>
         </div>
        </div>
        </div>
        </div>
        
      )}
      {updateMessage && <p className="text-success mt-2">{updateMessage}</p>}
    </div>
  );
};

export default UpdateOrder;

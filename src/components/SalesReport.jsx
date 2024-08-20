import React, { useState } from 'react';
import { salesReport } from '../services/ProductService'; 
import '../App.css';

const SalesReport = () => {
  const [report, setReport] = useState([]);
  const [errors, setErrors] = useState('');

  const handleGenerateReport = async () => {
    try {
      const response = await salesReport();
      setReport(response.data);
      setErrors('');
    } catch (error) {
      console.error('Error generating sales report:', error);
      setErrors('Failed to generate sales report.');
      setReport([]);
    }
  };

  return (
    <div className="container">
      

      {/* Generate Report Button */}
      <div className='text-center'>
        <button className='submit-btn mt-3' onClick={handleGenerateReport}>Generate Report</button>
        {errors && <p className="text-danger mt-2">{errors}</p>}
      </div>
     
      {/* Display Report */}
      {report.length > 0 && (
        <div className='mt-4'>
          <h5 className='text-center'>Sales Report</h5>
          <table className='table table-bordered mt-3 table-striped'>
            <thead>
              <tr>
                <th>Product ID</th>
                <th>Product Name</th>
                <th>Brand</th>
                <th>Category</th>
                <th>Stock Available</th>
                <th>Price</th>
                <th>Customer ID</th>
                <th>Order ID</th>
                <th>Ordered Quantity</th>
                <th>Amount Paid</th>
              </tr>
            </thead>
            <tbody>
              {report.map((item) => (
                <tr key={item.productId}>
                  <td>{item.productId}</td>
                  <td>{item.productName}</td>
                  <td>{item.brand}</td>
                  <td>{item.category}</td>
                  <td>{item.stockAvailable}</td>
                  <td>{item.price}</td>
                  <td>{item.customerId}</td>
                  <td>{item.orderId}</td>
                  <td>{item.orderedQuantity}</td>
                  <td>{item.amountPaid}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
};

export default SalesReport;

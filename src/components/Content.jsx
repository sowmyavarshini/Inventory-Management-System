import React from 'react';
import { BrowserRouter as Router, Routes, Route, NavLink } from 'react-router-dom';
import Home from './Home';
import AddProduct from './AddProduct';
import FindProductbyid from './FindProductbyid';
import ListAllProducts from './ListAllProducts';
import UpdateProduct from './UpdateProduct';
import AddOrder from './AddOrder';
import FindOrderByid from './FindOrderByid';
import ListAllOrders from './ListAllOrders';
import UpdateOrder from './UpdateOrder';
import FindProductByBrand from './FindProductByBrand';
import FindProductByCategory from './FindProductByCategory';
import CustomerReport from './CustomerReport';
import SalesReport from './SalesReport';


const apiButtons = [
  { name: 'Home', path: '/home' },
  { name: 'Add Product', path: '/add-product' },
  { name: 'Find product by id', path: '/find-product' },
  { name: 'Get Product List', path: '/get-product-list' },
  { name: 'Update Product', path: '/update-product' },
  { name: 'Create Order', path: '/create-order' },
  { name: 'Find Order by id', path: '/find-order' },
  { name: 'Get Order List', path: '/get-order-list' },
  { name: 'Update Order', path: '/update-order' },
  { name: 'Find Product by Brand', path: '/find-product-brand' },
  { name: 'Find Product by Category', path: '/find-product-category' },
  { name: 'Customer Report', path: '/customer-report' },
  { name: 'Sales Report', path: '/sales-report' },
];


const Content = () => {
  return (
    
      <div className="app">
        <div className="center-container">
          {/* Left Side: API Calls */}
          <div className="api-calls">
            {apiButtons.map((button, index) => (
              <NavLink 
                key={index} 
                to={button.path} 
                className={'api-button'}
              >
                {button.name}
              </NavLink>
            ))}
          </div>

          {/* Right Side: API Display */}
          <div className="api-display">
            <Routes>
              <Route path="/home" element={<Home />} />
              <Route path="/add-product" element={<AddProduct />} />
              <Route path="/find-product" element={<FindProductbyid />} />
              <Route path="/get-product-list" element={<ListAllProducts />}/>
             <Route path="/update-product" element={<UpdateProduct />} />
              <Route path="/create-order" element={<AddOrder />} />
              <Route path="/find-order" element={<FindOrderByid />} />
              <Route path="/get-order-list" element={<ListAllOrders />} />
              <Route path="/update-order" element={<UpdateOrder />} />
              <Route path="/find-product-brand" element={<FindProductByBrand />} />
              <Route path="/find-product-category" element={<FindProductByCategory />} />
              <Route path="/customer-report" element={<CustomerReport />} />
              <Route path="/sales-report" element={<SalesReport />} />
              <Route path="/" element={<div><h2>Select an API to see results.</h2></div>} />
            </Routes>
          </div>
        </div>
      </div>
    
  );
};

export default Content;









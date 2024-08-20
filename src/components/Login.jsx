import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import logo from '../assets/images/InvenTraklogo.jpg';
import {login} from '../services/ProductService';
import '../App.css'; 
import { toast } from 'react-toastify';

const Login = ({ onLogin }) => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Reset error state
    setError({ username: '', password: '', general: '' });

    let hasError = false;

    // Validation for non-blank fields
    if (!username.trim()) {
      setError(prevError => ({ ...prevError, username: 'Username cannot be blank' }));
      hasError = true;
    }
    if (!password.trim()) {
      setError(prevError => ({ ...prevError, password: 'Password cannot be blank' }));
      hasError = true;
    }

    if (hasError) {
      return;
    }

    try {
      // Call the login API
      const response = await login(username, password);
    
      if (response.data) {
        toast.success('Login successful');
        onLogin(username);
        navigate('/home'); // Redirect to the home page on successful login
      }else {
        const errorMessage = response.data;
        console.log("errorMessage");
        if (errorMessage.includes('User not found')) {
          setError(prevError => ({ ...prevError, username: 'User not found' }));
        } else if (errorMessage.includes('Password mismatch')) {
          setError(prevError => ({ ...prevError, password: 'Wrong password' }));
        } else {
          setError(prevError => ({ ...prevError, general: 'Invalid username or password' }));
        }
      }
    } catch (err) {
      console.error(err);
      const errorMessage = err.response?.data || 'Failed to login. Please try again.';

      if (errorMessage.includes('User not found')) {
        setError(prevError => ({ ...prevError, username: 'User not found' }));
      } else if (errorMessage.includes('Password mismatch')) {
        setError(prevError => ({ ...prevError, password: 'Wrong password' }));
      } else {
        setError(prevError => ({ ...prevError, general: errorMessage }));
      }
    }
    
  };


  return (
    <div className="login-container">
          <div className="login-card">
          <img src={logo} alt="InvenTrak Logo" className="login-logo" />
      <h2 className="name">InvenTrak</h2>
      <h5>Please login to your account</h5>
      <form onSubmit={handleSubmit}>
      
         <div>
          <input
            type="text"
            value={username}
            placeholder="Username"
            onChange={(e) => setUsername(e.target.value)}
              className="login-input"
          />
            {error.username && <p className="text-danger">{error.username}</p>}
            </div>
            <div>
          <input
            type="password"
            placeholder='Password'
            value={password}
            onChange={(e) => setPassword(e.target.value)}
              className="login-input"
          />
           {error.password && <p className="text-danger">{error.password}</p>}

            {error.general && <p className="text-danger">{error.general}</p>}
            </div>
        <button type="submit"className='login-btn'>Login</button>
      </form>
      <p>Don't have an account? <a href="/register">Register</a></p>
    </div>
    </div>
  );
};

export default Login;

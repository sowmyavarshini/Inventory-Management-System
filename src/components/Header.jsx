import React from 'react'
import logo from '../assets/images/InvenTraklogo.jpg'

const Header = ({ username, onLogout }) => {
  return (
    <div>
       <header className='header'>
        <nav class="navbar">
            <div class="navbar-left">
                <img src={logo} alt="Logo" class="logo" />
                <span class="brand-name">InvenTrak</span>
            </div>
            <div class="navbar-right">
                <button class="username-btn">Username:{username}</button>
                <button class="logout-btn"  onClick={onLogout}>Logout</button>
            </div>
        </nav>
    </header>
  </div>
  )
}

export default Header
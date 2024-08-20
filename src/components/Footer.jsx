import React from 'react'
import { FaFacebookF, FaInstagram, FaGithub, FaTwitter, FaLinkedinIn } from 'react-icons/fa';
import { AiOutlineCopyright } from 'react-icons/ai';

const Footer = () => {
  return (
    <>
        <footer className="footer">
      <div className="social-icons">
        <a href="https://facebook.com" className="icon facebook"><FaFacebookF /></a>
        <a href="https://instagram.com" className="icon instagram"><FaInstagram /></a>
        <a href="https://github.com" className="icon github"><FaGithub /></a>
        <a href="https://twitter.com" className="icon twitter"><FaTwitter /></a>
        <a href="https://linkedin.com" className="icon linkedin"><FaLinkedinIn /></a>
      </div>
      <div className="footer-text"> <AiOutlineCopyright /> 2024 InvenTrak. All rights reserved.</div>
    </footer>
    </>
  )
}

export default Footer
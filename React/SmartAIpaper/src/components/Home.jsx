import React from 'react'
import "../styles/home.css"
import FileUploader from './FileUploader'
function Home() {
  return (
    <div className='home'>
        <div className='heading'>Welcome to SmartAIPaper</div>
        <div className='intro-text'><b>SmartAIpaper</b> is an AI-powered platform designed to simplify question paper creation. Upload sample question papers in PDF format, and let our advanced AI extract, categorize, and generate a brand-new, well-structured question paper in seconds </div>
        <FileUploader/>
    </div>
  )
}

export default Home
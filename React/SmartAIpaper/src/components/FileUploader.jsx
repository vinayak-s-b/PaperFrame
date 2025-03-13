import React, { useState } from 'react'
import axios from "axios";
import "../styles/fileUploader.css"
function FileUploader() {
    const divStyle={
        color: 'white'
    }
    const[files, setFiles]=useState(null);
    const[status,setStatus]=useState("Idle");
    const[pdfBlob,setPdfBlob]=useState(null);
    function handleFileChange(e ){
        setStatus('idle');
        if(e.target.files){
            setFiles(e.target.files);
        }
    }
   async function handleUpload(e){
    e.preventDefault();
        if(!files){
            return alert("Please upload a PDF file");
        }
        setStatus("Uploading");
        const formData= new FormData();
        for(let i=0;i<files.length;i++){
            formData.append(`files`,files[i]);
            console.log(files);
        }
        try {
            const response = await axios.post("http://localhost:8080/generate-pdf", formData, {
                headers : {"Content-Type": "multipart/form-data"},
              responseType: "blob", // Expect a PDF file
            });
            setPdfBlob(response.data);
            
            setStatus("success");
          } catch (error) {
            setStatus("error");
            console.error("Error generating PDF", error);
          }
        // await axios.post("http://localhost:8080/generate-pdf",formData,{
        //    headers : {
        //         "Content-Type": "multipart/form-data",
        //     }
        // }).then(res=>{
        //     setStatus("success");
        //     console.log(res.data);
        // }).catch(err=>{
        //     setStatus("error");
        //     console.log(` ${status} Error Uploading fIle`);
        // })
    }
    function handleDownload(){
        // Create a download link
        const blob = new Blob([pdfBlob], { type: "application/pdf" });
        const link = document.createElement("a");
        link.href = URL.createObjectURL(blob);
        link.download = "Generated_Question_Paper.pdf";
        link.click();
    }
  return (
    <div  className="fileContent" >
        <form onSubmit={handleUpload}>
            <div className='input-pdf'>
                <input type='file' accept='application/pdf' onChange={handleFileChange} multiple/>
            </div>
            {files && status!=="Uploading" && <button type="submit">Upload</button>} 
        </form>
       <div className="message" >
            {status==="Uploading"&&<p>Uploading...</p>}
            {status==="success" && <p>File Uploaded Successfully</p>}
            {status==="error" && <p>Failed to upload file</p>}
       </div>
      {pdfBlob && <button onClick={handleDownload}>Download</button>}
    </div>
  )
}

export default FileUploader
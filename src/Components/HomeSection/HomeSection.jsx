import React, { useState } from 'react';
import { Avatar, Button } from '@mui/material';
import { useFormik } from 'formik';
import * as Yup from 'yup';
import ImageIcon from '@mui/icons-material/Image';
import FmdGoodIcon from '@mui/icons-material/FmdGood';
import TagFacesIcon from '@mui/icons-material/TagFaces';
import TweetCard from './TweetCard';

const HomeSection = () => {
    const [uploadingImage, setUploadingImage] = useState(false);
    const [selectedImage, setSelectedImage] = useState("");

    const validationSchema = Yup.object().shape({
        content: Yup.string().required('Tweet is required')
    });

    const handleSubmit = (values) => {
        console.log("value", values);
    }

    const formik = useFormik({
        initialValues: {
            content: '',
            image: '',
        },
        onSubmit: handleSubmit,
        validationSchema,
    });

    const handleSelectImage = (event) => {
        setUploadingImage(true);
        const imgURL = event.target.files[0]; // Sửa từ "flie" thành "files"
        formik.setFieldValue('image', imgURL);
        setSelectedImage(URL.createObjectURL(imgURL)); // Hiển thị ảnh đã chọn
        setUploadingImage(false);
    }

    return (
        <div>
            <div className='space-y-5'>
                <section>
                    <h1 className='py-5 text-xl font-bold opacity-90 '>Home</h1>
                </section>
                <section className={'pb-10'}>
                    <div className='flex space-5'>
                        <Avatar
                            alt="username"
                            src="https://cdn.pixabay.com/photo/2020/07/01/12/58/icon-5359553_960_720.png"
                        />
                    </div>
                    <div className='w-full'>
                        <form onSubmit={formik.handleSubmit}>
                            <div className='flex space-x-5'>
                                <input
                                    type="text" 
                                    name='content' 
                                    placeholder='What is happening?'
                                    className='border-none outline-none text-lg bg-transparent w-full'
                                    {...formik.getFieldProps("content")}
                                />
                                {formik.errors.content && formik.touched.content && (
                                    <span className='text-red-500'>{formik.errors.content}</span>
                                )}
                            </div>
                            <div className='flex justify-between items-center mt-5'>
                                <div className='flex space-x-5 items-center'>
                                    <label className='flex items-center space-x-2 rounded-md cursor-pointer'>
                                       <ImageIcon className='text-[#1d9bf0]' />
                                    <input 
                                        type='file' 
                                        name='imageFile' 
                                        className='hidden' 
                                        onChange={handleSelectImage} 
                                    /> 
                                    </label>
                                    <FmdGoodIcon className='text-[#1d9bf0]' />
                                    <TagFacesIcon className='text-[#1d9bf0]' /> 
                                </div>
                                {selectedImage && (
                                    <div>
                                        <img src={selectedImage} alt="Selected" className="h-24 w-24 object-cover"/>
                                    </div>
                                )}
                            </div>
                            <div>
                                <Button sx={{
                                    width: "100%",
                                    borderRadius: "20px",
                                    py: "8px",
                                    px: "20px",
                                    bgcolor: '#1e88e5'
                                }} variant='contained' type='submit'>
                                    Tweet
                                </Button>
                            </div>
                        </form>
                    </div>
                </section>
                <section>
                    <TweetCard />
                </section>
            </div>
        </div>
    );
};

export default HomeSection;

import React from 'react';
import './TweetCard.css';
import RepeatOnIcon from '@mui/icons-material/RepeatOn';
import { Avatar } from '@mui/material';
import { useNavigate } from 'react-router-dom'; // Thêm import useNavigate

const TweetCard = ({ tweet }) => {
    const navigate = useNavigate();
    
    return (
        <div className="tweet-card">
            {/* <div className='flex items-center font-semibold text-gray-700 py-2'>
                <RepeatOnIcon />
            </div> */}

            <div className='flex space-x-5'>
                <Avatar
                    onClick={() => navigate(`/profile/${tweet.user.id}`)}
                    className='cursor-pointer'
                    alt='username'
                    src='https://cdn.pixabay.com/photo/2020/07/01/12/58/icon-5359553_960_720.png'
                />
                <div className='w-full'>
                    <div className='flex justify-between items-center'>
                        <div className='flex cursor-pointer items-center space-x-2'>
                            <span className='font-semibold'>Code With Me</span>
                            <span className='text-gray-600'>@koomi · 2m</span> {/* Sửa span và dấu chấm */}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default TweetCard;

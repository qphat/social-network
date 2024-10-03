import React from 'react';
import { navigationMenu } from './NavigationMenu';
import { useNavigate } from 'react-router-dom';
import { Button, Menu, MenuItem, Avatar } from '@mui/material';
import MoreHorizIcon from '@mui/icons-material/MoreHoriz';

const Navigation = () => {
    const navigate = useNavigate();
    const [anchorEl, setAnchorEl] = React.useState(null);
    const open = Boolean(anchorEl);

    const handleClick = (event) => {
        setAnchorEl(event.currentTarget);
    };

    const handleClose = () => {
        setAnchorEl(null);
    };

    const handleLogout = () => {
        console.log('Logout');
        handleClose();
    };
    
    return (
        <div className='h-screen sticky top-0'>
            <div>
                <div className="py-5">
                    <svg 
                        height="30" 
                        width="30" 
                        viewBox="0 0 24 24" 
                        aria-hidden="true" 
                        className="r-jwli3a r-4qtqp9 r-yyyyoo r-labphf r-1777fci r-dnmrzs r-494qqr r-bnwqim r-1plcrui r-lrvibr"
                    >
                        <g>
                            <path d="M18.244 2.25h3.308l-7.227 8.26 8.502 11.24H16.17l-5.214-6.817L4.99 21.75H1.68l7.73-8.835L1.254 2.25H8.08l4.713 6.231zm-1.161 17.52h1.833L7.084 4.126H5.117z"></path>
                        </g>
                    </svg>
                </div>
                <div className='space-y-6'>
                    {navigationMenu.map((item, index) => (
                        <div 
                            key={index} // Thêm key cho mỗi item
                            className='cursor-pointer flex space-x-3 items-center' 
                            onClick={() => item.title === "Profile" ? navigate(`/profile/${5}`) : navigate(item.path)}
                        >
                            {item.icon}  
                            <p className='text-xl'>{item.title}</p> 
                        </div>
                    ))}
                </div>
                <div className='py-10'>
                    <Button
                        sx={{width: "70%", borderRadius: "29px", py: "15px", bgcolor:'#1e88e5'}}
                        variant='contained'
                    >
                        Tweet
                    </Button>
                </div>
                <div className='flex items-center justify-between'>
                    <div className='flex items-center space-x-3'>
                        <Avatar
                            alt="username"
                            src="https://cdn.pixabay.com/photo/2020/07/01/12/58/icon-5359553_960_720.png"
                        /> 
                        <div>
                            <span>Koomi luv U</span>
                            <span className='opacity-70 block'>@koomi</span>
                        </div> 
                        <Button
                            id="basic-button"
                            aria-controls={open ? 'basic-menu' : undefined}
                            aria-haspopup="true"
                            aria-expanded={open ? 'true' : undefined}
                            onClick={handleClick}
                        >
                            <MoreHorizIcon /> 
                        </Button>
                        <Menu
                            id="basic-menu"
                            anchorEl={anchorEl}
                            open={open}
                            onClose={handleClose}
                            MenuListProps={{
                                'aria-labelledby': 'basic-button',
                            }}
                        >
                            <MenuItem onClick={handleLogout}>Logout</MenuItem>
                        </Menu>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Navigation;

import { useState } from "react";

// react-router-dom components
import { Link } from "react-router-dom";

import Card from "@mui/material/Card";
import Switch from "@mui/material/Switch";
import Grid from "@mui/material/Grid";
import MuiLink from "@mui/material/Link";

// Material Kit 2 React components
import MKBox from "components/MKBox";
import MKTypography from "components/MKTypography";
import MKInput from "components/MKInput";
import MKButton from "components/MKButton";

import {useNavigate} from 'react-router-dom';

function Register() {
  const navigate = useNavigate();
  const handleRegisterClick = () => navigate('/sign-in', {replace: true});
  return (
    <>
      <MKBox
        minHeight="100vh"
        width="100%"
        sx={{
          backgroundColor: "#efaf96",
          backgroundSize: "cover",
          backgroundPosition: "top",
          display: "grid",
          placeItems: "center",
        }}
      >
       <Grid container spacing={1} justifyContent="center" alignItems="center" height="100%">
                 <Grid item xs={11} sm={9} md={5} lg={4} xl={3}>
                   <Card>
                     <MKBox
                       bgColor="info"
                       borderRadius="lg"
                       coloredShadow="info"
                       mx={2}
                       mt={-3}
                       p={2}
                       mb={1}
                       textAlign="center"
                     >
                       <MKTypography variant="h4" fontWeight="medium" color="white">
                         Sign up
                       </MKTypography>
                     </MKBox>
                     <MKBox pt={4} pb={3} px={3}>
                       <MKBox component="form" role="form">
                         <MKBox mb={2}>
                           <MKInput type="email" label="Email" fullWidth />
                         </MKBox>
                         <MKBox mb={2}>
                           <MKInput type="password" label="Password" fullWidth />
                         </MKBox>
                         <MKBox mt={4} mb={1}>
                           <MKButton onClick={handleRegisterClick} color="info" fullWidth>
                             register
                           </MKButton>
                         </MKBox>
                       </MKBox>
                     </MKBox>
                   </Card>
                 </Grid>
               </Grid>
      </MKBox>
    </>
  );
}

export default Register;

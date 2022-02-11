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
import UserContext from "components/UserContext";
import {useNavigate} from 'react-router-dom';


function Login() {
  const [rememberMe, setRememberMe] = useState(false);
  const navigate = useNavigate();
  const handleSetRememberMe = () => setRememberMe(!rememberMe);
  const handleSignIn = () => {UserContext.isLoggedIn.dispatch({type: true}); navigate('/home', {replace: true});};
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
                         Sign in
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
                         <MKBox display="flex" alignItems="center" ml={-1}>
                           <Switch checked={rememberMe} onChange={handleSetRememberMe}/>
                           <MKTypography
                             variant="button"
                             fontWeight="regular"
                             color="text"
                             onClick={handleSetRememberMe}
                             sx={{ cursor: "pointer", userSelect: "none", ml: -1 }}
                           >
                             &nbsp;&nbsp;Remember me
                           </MKTypography>
                         </MKBox>
                         <MKBox mt={4} mb={1}>
                           <MKButton onClick={handleSignIn} color="info" fullWidth>
                             sign in
                           </MKButton>
                         </MKBox>
                         <MKBox mt={3} mb={1} textAlign="center">
                           <MKTypography variant="button" color="text">
                             Don&apos;t have an account?{" "}
                             <MKTypography
                               component={Link}
                               to="/register"
                               variant="button"
                               color="info"
                               fontWeight="medium"
                               textGradient
                             >
                               Sign up
                             </MKTypography>
                           </MKTypography>
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

export default Login;

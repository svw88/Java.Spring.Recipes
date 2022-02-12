import { useEffect, useState } from "react";

import Container from "@mui/material/Container";
import Grid from "@mui/material/Grid";

import MKTypography from "components/MKTypography";
import MKBox from "components/MKBox";

import DefaultNavbar from "components/DefaultNavbar";
import UserContext from "components/UserContext";

import routes from "routes";

function MyRecipes() {
  const [displayedRoutes, setDisplayedRoutes] = useState(routes.filter(route => route.requiresLogin == UserContext.isLoggedIn.getState().value));
      useEffect(() => {
          const unsubscribe = UserContext.isLoggedIn.subscribe(() => setDisplayedRoutes(routes.filter(route => route.requiresLogin == UserContext.isLoggedIn.getState().value)));
          return function cleanup() {
            unsubscribe();
          };
        });
  return (
    <>
          <DefaultNavbar
              routes={displayedRoutes}
              sticky
              brand="Flava"
          />
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
        <Container>
          <Grid container item xs={12} lg={7} justifyContent="center" mx="auto">
            <MKTypography
              variant="h1"
              color="white"
              mt={-6}
              mb={1}
              sx={({ breakpoints, typography: { size } }) => ({
                [breakpoints.down("md")]: {
                  fontSize: size["3xl"],
                },
              })}
            >
              My Recipes
            </MKTypography>
          </Grid>
        </Container>
      </MKBox>
    </>
  );
}

export default MyRecipes;

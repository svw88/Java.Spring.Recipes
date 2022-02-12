import { useEffect, useState, Fragment } from "react";
import Box from '@mui/material/Box';
import Stepper from '@mui/material/Stepper';
import Step from '@mui/material/Step';
import StepLabel from '@mui/material/StepLabel';
import Button from '@mui/material/Button';

import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";
import List from "@mui/material/List";
import ListItem from "@mui/material/ListItem";
import ListItemText from "@mui/material/ListItemText";

import MKTypography from "components/MKTypography";
import MKBox from "components/MKBox";
import MKInput from "components/MKInput";
import MKButton from "components/MKButton";

import DefaultNavbar from "components/DefaultNavbar";
import UserContext from "components/UserContext";

import routes from "routes";
import { Icon, IconButton, StepContent } from "@mui/material";

function AddRecipe() {
    const [displayedRoutes, setDisplayedRoutes] = useState(routes.filter(route => route.requiresLogin == UserContext.isLoggedIn.getState().value));
    const [category, setCategory] = useState(routes.filter(route => route.requiresLogin == UserContext.isLoggedIn.getState().value));
    useEffect(() => {
        const unsubscribe = UserContext.isLoggedIn.subscribe(() => setDisplayedRoutes(routes.filter(route => route.requiresLogin == UserContext.isLoggedIn.getState().value)));
        return function cleanup() {
            unsubscribe();
        };
    });

    const handleCategoryChange = (event) => setCategory(event.target.value);

    const [activeStep, setActiveStep] = useState(0);
    const [skipped, setSkipped] = useState(new Set());

    const isStepOptional = (step) => {
        return false;
    };

    const isStepSkipped = (step) => {
        return skipped.has(step);
    };

    const handleNext = () => {
        if(activeStep == 2) return;
        let newSkipped = skipped;
        if (isStepSkipped(activeStep)) {
            newSkipped = new Set(newSkipped.values());
            newSkipped.delete(activeStep);
        }
        
        setActiveStep((prevActiveStep) => prevActiveStep + 1);
        setSkipped(newSkipped);
    };

    const handleBack = () => {
        setActiveStep((prevActiveStep) => prevActiveStep - 1);
    };

    const handleSkip = () => {
        if (!isStepOptional(activeStep)) {
            // You probably want to guard against something like this,
            // it should never occur unless someone's actively trying to break something.
            throw new Error("You can't skip a step that isn't optional.");
        }

        setActiveStep((prevActiveStep) => prevActiveStep + 1);
        setSkipped((prevSkipped) => {
            const newSkipped = new Set(prevSkipped.values());
            newSkipped.add(activeStep);
            return newSkipped;
        });
    };

    const handleReset = () => {
        setActiveStep(0);
    };
    return (
        <>
            <DefaultNavbar
                routes={displayedRoutes}
                sticky
                brand="Flava"
            />
            <MKBox minHeight="100vh"
                width="100%"
                sx={{
                    backgroundColor: "#efaf96",
                    backgroundSize: "cover",
                    backgroundPosition: "top",
                    display: "grid",
                    placeItems: "center",
                }}>
                <Grid container spacing={1} justifyContent="center" alignItems="center" height="100%">
                    <Grid item xs={11} sm={9} md={7} lg={6} xl={5}>
                        <Card>
                            <Grid container item justifyContent="center" xs={10} lg={7} mx="auto" mb={1} textAlign="center">

                                <MKTypography mt={4} variant="h3" mb={1}>
                                   Add Recipe
                                </MKTypography>
                            </Grid>
                            <Grid container item xs={12} lg={10} sx={{ mx: "auto" }}>
                                <MKBox width="100%" component="form" method="post" autocomplete="off">
                                    <Stepper activeStep={activeStep} sx={{p:1}}>
                                        <Step key="Details">
                                            <StepLabel>Details</StepLabel>

                                        </Step>
                                        <Step key="Ingredients">
                                            <StepLabel>Ingredients</StepLabel>
                                        </Step>
                                        <Step key="Directions">
                                            <StepLabel>Directions</StepLabel>
                                        </Step>
                                    </Stepper>
                                    <MKBox p={3}>

                                        <Grid container spacing={3}>
                                            {activeStep === 0 ? (
                                                <Fragment>
                                                    <Grid item xs={12}>
                                                        <MKInput variant="standard" label="Name" fullWidth />
                                                    </Grid>
                                                    <Grid item xs={12}>
                                                        <FormControl variant="standard" fullWidth >
                                                            <InputLabel>Category</InputLabel>
                                                            <Select
                                                                value={category}
                                                                label="Category"
                                                                onChange={handleCategoryChange}
                                                            >
                                                                <MenuItem value={"Breakfast"}>Breakfast</MenuItem>
                                                                <MenuItem value={"Lunch"}>Lunch</MenuItem>
                                                                <MenuItem value={"Dinner"}>Dinner</MenuItem>
                                                                <MenuItem value={"Desert"}>Desert</MenuItem>
                                                                <MenuItem value={"Snack"}>Snack</MenuItem>
                                                                <MenuItem value={"Drink"}>Drink</MenuItem>
                                                            </Select>
                                                        </FormControl>
                                                    </Grid>
                                                    <Grid item xs={12}>
                                                        <MKInput label="Description" multiline fullWidth rows={3} />
                                                    </Grid>
                                                </Fragment>
                                            ) : activeStep === 1 ? (
                                                <Fragment>
                                                    <Grid container item xs={12}>
                                                        <Grid item xs={8} pr={2}>
                                                            <MKInput variant="standard" label="Ingredient" fullWidth />
                                                        </Grid>
                                                        <Grid item xs={3}>
                                                            <FormControl variant="standard" fullWidth >
                                                                <InputLabel>Unit</InputLabel>
                                                                <Select
                                                                    label="Category"
                                                                >
                                                                    <MenuItem value={"spoon"}>spoon</MenuItem>
                                                                    <MenuItem value={"teaspoon"}>teaspoon</MenuItem>
                                                                    <MenuItem value={"ml"}>ml</MenuItem>
                                                                    <MenuItem value={"g"}>g</MenuItem>
                                                                </Select>
                                                            </FormControl>
                                                        </Grid>
                                                        <Grid item xs={1}>
                                                            <IconButton edge="end" size="large" aria-label="add">
                                                                <Icon>add</Icon>
                                                            </IconButton>
                                                        </Grid>
                                                    </Grid>
                                                    <Grid item xs={12}>
                                                        <List dense={true} sx={{ p: 1, border: '1px solid rgba(0, 0, 0, 0.125)', borderRadius: '0.375rem', minHeight: 146 }}>
                                                            <ListItem
                                                                secondaryAction={
                                                                    <IconButton edge="end" aria-label="delete">
                                                                        <Icon>delete</Icon>
                                                                    </IconButton>
                                                                }
                                                            >
                                                                <Grid container spacing={1}>
                                                                    <Grid item xs={9}>
                                                                        <ListItemText
                                                                            primary="Name"
                                                                        />
                                                                    </Grid>
                                                                    <Grid item xs={3}>
                                                                        <ListItemText
                                                                            primary="Unit"
                                                                        />
                                                                    </Grid>
                                                                </Grid>
                                                            </ListItem>
                                                        </List>
                                                    </Grid>
                                                </Fragment>
                                            ) : (
                                                <Fragment>
                                                    <Grid container item xs={12}>
                                                        <Grid item xs={11}>
                                                            <MKInput variant="standard" label="Direction" fullWidth />
                                                        </Grid>
                                                        <Grid item xs={1}>
                                                            <IconButton edge="end" size="large" aria-label="add">
                                                                <Icon>add</Icon>
                                                            </IconButton>
                                                        </Grid>
                                                    </Grid>
                                                    <Grid item xs={12}>
                                                        <List dense={true} sx={{ p: 1, border: '1px solid rgba(0, 0, 0, 0.125)', borderRadius: '0.375rem', minHeight: 146 }}>
                                                            <ListItem
                                                                secondaryAction={
                                                                    <IconButton edge="end" aria-label="delete">
                                                                        <Icon>delete</Icon>
                                                                    </IconButton>
                                                                }
                                                            >
                                                                <ListItemText
                                                                    primary="Name"
                                                                />
                                                            </ListItem>
                                                        </List>
                                                    </Grid>
                                                </Fragment>
                                            )}
                                        </Grid>
                                        <Box sx={{ display: 'flex', flexDirection: 'row', pt: 2 }}>
                                            <Button
                                                color="inherit"
                                                disabled={activeStep === 0}
                                                onClick={handleBack}
                                                sx={{ mr: 1 }}
                                            >
                                                Back
                                            </Button>
                                            <Box sx={{ flex: '1 1 auto' }} />
                                            {isStepOptional(activeStep) && (
                                                <Button color="inherit" onClick={handleSkip} sx={{ mr: 1 }}>
                                                    Skip
                                                </Button>
                                            )}

                                            <Button onClick={handleNext}>
                                                {activeStep > 1 ? 'Finish' : 'Next'}
                                            </Button>
                                        </Box>
                                    </MKBox>
                                </MKBox>
                            </Grid>
                        </Card>
                    </Grid>
                </Grid>
            </MKBox>
        </>
    );
}

export default AddRecipe;

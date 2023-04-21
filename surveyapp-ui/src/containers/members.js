import React, {useState} from "react";
import {withRouter} from 'react-router'
import axios from 'axios';
import IconButton from "@material-ui/core/IconButton";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import InputAdornment from "@material-ui/core/InputAdornment";
import SearchIcon from "@material-ui/icons/Search";
import TableHead from "@material-ui/core/TableHead";
import TableRow from "@material-ui/core/TableRow";
import Table from "@material-ui/core/Table";
import TableCell from "@material-ui/core/TableCell";
import TableBody from "@material-ui/core/TableBody";
import Modal from '@material-ui/core/Modal';
import { makeStyles } from '@material-ui/core/styles';

function rand() {
    return Math.round(Math.random() * 20) - 10;
}
function getModalStyle() {
    const top = 50 + rand();
    const left = 50 + rand();
    return {
        top: `${top}%`,
        left: `${left}%`,
        transform: `translate(-${top}%, -${left}%)`,
    };
}
const useStyles = makeStyles(theme => ({
    modal: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
    },
    paper: {
        position: 'absolute',
        width: 450,
        backgroundColor: theme.palette.background.paper,
        boxShadow: theme.shadows[5],
        padding: theme.spacing(2, 4, 3),
    },
}));

const Members = (props) => {
    const [APIData, setAPIData] = useState([])
    const [APISCData, setAPISCData] = useState([])
    const [APIPLData, setAPIPLData] = useState([])
    const [searchInput, setSearchInput] = useState('');
    const classes = useStyles();
    const [modalStyle] = React.useState(getModalStyle);
    const [open1, setOpen1] = React.useState(false);
    const [open2, setOpen2] = React.useState(false);
    
    const handleOpen1 = (id) => {
        searchSurveysCompleted(id);
        setOpen1(true);
    };
    const handleOpen2 = (id) => {
        searchPointsList(id);
        setOpen2(true);
    };
    const handleClose1 = () => {
        setOpen1(false);
    };
    const handleClose2 = () => {
        setOpen2(false);
    };

    const search = query => {
        const url = `http://localhost:8080/api/members/${query}`;
    
        axios.get(url)
           .then((response) => {
            console.log(response.data)
              setSearchInput(query);
              setAPIData(response.data);
           })
    };

    const searchSurveysCompleted = query => {
        const url = `http://localhost:8080/api/surveys/completed-surveys/${query}`;
    
        axios.get(url)
           .then((response) => {
            console.log(response.data)
            setAPISCData(response.data);
           })
    };

    const searchPointsList = query => {
        const url = `http://localhost:8080/api/surveys/points-list/${query}`;
    
        axios.get(url)
           .then((response) => {
            console.log(response.data)
            setAPIPLData(response.data);
           })
    };

    const onChange = (e) => {
        const { value } = e.target;
        if(e.key === 'Enter' && value !== '' && !isNaN(value)) {
            search(value);
        }
    }

    return (
        <div>
        <div style={{ width: '100%', padding: 25 }}>
            <TextField
                label="Search Members"
                onKeyUp={onChange}
                InputProps={{
                    endAdornment: (
                    <InputAdornment position="start">
                        <IconButton onClick={onChange}>
                        <SearchIcon />
                        </IconButton>
                    </InputAdornment>
                    )
                }}
            />
        </div>
        {(searchInput.length > 0) ?
                            APIData !== null ? 
                            <div style={{
                                display: "flex",
                                alignItems: "center",
                                height: "100%"
                              }}>
                                <Table aria-label="simple table" stickyHeader style={{ width: '70%', margin: 'auto' }}>
                                <TableHead>
                                <TableRow>
                                    <TableCell>Member Id</TableCell>
                                    <TableCell align="right">Full name</TableCell>
                                    <TableCell align="right">E-mail</TableCell>
                                    <TableCell align="right">Active</TableCell>
                                    <TableCell align="right">Surveys completed</TableCell>
                                    <TableCell align="right">Points list</TableCell>
                                </TableRow>
                                </TableHead>
                                <TableBody>
                                    <TableRow key={1}>
                                    <TableCell component="th" scope="row">
                                        {APIData.id}
                                    </TableCell>
                                    <TableCell align="right">{APIData.fullname}</TableCell>
                                    <TableCell align="right">{APIData.email}</TableCell>
                                    <TableCell align="right">{APIData.active}</TableCell>
                                    <TableCell align="center"><Button color="primary" variant="contained" onClick={e => handleOpen1(APIData.id)}>show</Button></TableCell>
                                    <TableCell align="center"><Button color="primary" variant="contained" onClick={e => handleOpen2(APIData.id)}>show</Button></TableCell>
                                    </TableRow>
                                </TableBody>
                                </Table>
                                <Modal
                                    aria-labelledby="simple-modal-title"
                                    aria-describedby="simple-modal-description"
                                    open={open1}
                                    onClose={handleClose1}>
                                    <div style={modalStyle} className={classes.paper}>
                                    <Table aria-label="simple table" stickyHeader style={{ width: '100%', margin: 'auto' }}>
                                        <TableHead>
                                        <TableRow>
                                            <TableCell>Name</TableCell>
                                            <TableCell align="right">Expected Completes</TableCell>
                                            <TableCell align="right">Completion Points</TableCell>
                                            <TableCell align="right">Filtered Points</TableCell>
                                        </TableRow>
                                        </TableHead>
                                        <TableBody>
                                            {APISCData.map((row) => (
                                            <TableRow>
                                                <TableCell component="th" scope="row">
                                                {row.name}
                                                </TableCell>
                                                <TableCell align="right">{row.expectedCompletes}</TableCell>
                                                <TableCell align="right">{row.completionPoints}</TableCell>
                                                <TableCell align="right">{row.filteredPoints}</TableCell>
                                            </TableRow>
                                            ))}
                                        </TableBody>
                                    </Table>
                                    </div>
                                </Modal>

                                <Modal
                                    aria-labelledby="simple-modal-title"
                                    aria-describedby="simple-modal-description"
                                    open={open2}
                                    onClose={handleClose2}>
                                    <div style={modalStyle} className={classes.paper}>
                                    <Table aria-label="simple table" stickyHeader style={{ width: '100%', margin: 'auto' }}>
                                        <TableHead>
                                        <TableRow>
                                            <TableCell>Name</TableCell>
                                            <TableCell align="right">Expected Completes</TableCell>
                                            <TableCell align="right">Completion Points</TableCell>
                                            <TableCell align="right">Filtered Points</TableCell>
                                        </TableRow>
                                        </TableHead>
                                        <TableBody>
                                            {APIPLData.map((row) => (
                                            <TableRow>
                                                <TableCell component="th" scope="row">
                                                {row.name}
                                                </TableCell>
                                                <TableCell align="right">{row.expectedCompletes}</TableCell>
                                                <TableCell align="right">{row.completionPoints}</TableCell>
                                                <TableCell align="right">{row.filteredPoints}</TableCell>
                                            </TableRow>
                                            ))}
                                        </TableBody>
                                    </Table>
                                    </div>
                                </Modal>
                            </div>
                            : <div style={{display: "flex", alignItems: "center", height: "100%"}}>Member not found</div>
                            : <div></div>}
                            </div>
    )

}

export default withRouter(Members);

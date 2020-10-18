import React, { useState} from 'react';
import {
    Button,
    Modal,
    ModalHeader,
    ModalBody,
    ModalFooter,
    InputGroup,
    InputGroupAddon,
    Input,
} from 'reactstrap';
import PropTypes from 'prop-types';
// <Item id ='new' assetId ={assetId} modalId = {modalId}/>



/*
 *  Add a new asset / liability, or edit asset / liability
 * fetches asset from id and populates fields if user selects Edit
* */
let editApi ='/api/asset/'
const ModalManger = (props) => {
    const {
        handleClose,
        show,
        Title,
        fetchdata,
        modal
    } = props;



    const handleOnChangeName = (event) => {
        let Newmodal = {
            name : asset.name ,
            amount: asset.amount ,
            id : asset.id
        }
        Newmodal.name = event.target.value
        setAsset(Newmodal)
    }
     const handleOnChangeAmount = (event) => {
        let Newmodal = {
            name : asset.name,
            amount: asset.amount,
            id : asset.id
        }
        Newmodal.amount = event.target.value
        setAsset(Newmodal)

    }
    const handleSubmit = (event) => {
        event.preventDefault();

        if (modal.id === undefined){
            console.log('oncreate')
            onCreate()
        }else{
            console.log('onEdit')
            onEdit()
        }
        handleClose()
    }

     const onCreate =  () => {
        // POST request for adding new asset/liability to db
        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({name: asset.name, amount: asset.amount})
        };
         fetch(`${editApi}/${modal.category}`, requestOptions)
            .then( response => {
                const data =  response.json();

                // check for error response
                if (!response.ok) {
                    // get error message from body or default to response status
                    const error = (data && data.message) || response.status;
                    return Promise.reject(error);
                }


            }).then(() => {
                fetchdata()
            })
            .catch(error => {
                console.error('There was an error!', error);
            });
         console.log('sucess2')

    }
    const onEdit = async () => {
        // POST request for adding new asset/liability to db
        const requestOptions = {
            method: 'PUT',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({name: asset.name, amount: asset.amount})
        };
        let ComposedEditAPI = `${editApi}${asset.id}/${modal.category}`
        await fetch(ComposedEditAPI, requestOptions)
            .then(async response => {
                const data = await response.json();

                // check for error response
                if (!response.ok) {
                    // get error message from body or default to response status
                    const error = (data && data.message) || response.status;
                    return Promise.reject(error);
                }

                //  this.setState({ postId: data.id })
            }).then(() => {
                fetchdata()
            })
            .catch(error => {
                //this.setState({ errorMessage: error.toString() });
                console.error('There was an error!', error);
            });

    }

    const showHideClassName = show ? "modal display-block" : "modal display-none";
    const [asset , setAsset] = useState(modal);
    return (

        <div className={showHideClassName}>
            <Modal isOpen={show}>
                <ModalHeader toggle={handleClose}>{Title}</ModalHeader>
                <ModalBody>
                    <div>
                        <InputGroup >
                            <Input type="text"  placeholder="Enter asset / Libility name" value = {asset.name} onChange = {handleOnChangeName}   />
                        </InputGroup>
                        <br />
                        <InputGroup>
                            <InputGroupAddon addonType="prepend">$</InputGroupAddon>
                            <Input placeholder="Amount" min={0} max={1000000} type="number"  step="1" value = {asset.amount} onChange = {handleOnChangeAmount} />
                        </InputGroup>
                    </div>


                </ModalBody>
                <ModalFooter>
                    <Button color="primary" onClick={handleSubmit}>Add</Button>{' '}
                    <Button color="secondary" onClick={handleClose}>Cancel</Button>
                </ModalFooter>
            </Modal>
        </div>
    );
}


export default ModalManger;

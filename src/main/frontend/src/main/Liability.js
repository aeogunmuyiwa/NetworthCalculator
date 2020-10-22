import React, {Component, useState} from "react";
import {Button, ButtonGroup, Container, Table} from "reactstrap";
import Modalpopup from '../Assets/AssetModal';
import Keys from '../APis';

class Liability extends Component{
    modal = {
        name : '',
        amount: '',
        id : '',
        category : '',
    }

    APIkEYS = {
        delete : '',
        getList : '',
        add: ''
    }

    constructor(props) {
        super(props);
        this.state = {List: this.props.list, SelectedAsset : {}, isLoading: true, show: false,modal : this.modal, category : this.props.category};
        if (this.state.List !== undefined ){
            this.setState({isLoading : false})
        }
        this.remove = this.remove.bind(this);
    }



    componentDidMount() {
        this.setState({isLoading: false, List: this.props.list});
    }


    async remove(asset) {

        await fetch(`${Keys.ListAssetApi}${asset.assesId}/${this.state.category}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            this.props.fetchdata()
        });
    }
    showModal = () => {

        this.setState({ show: true});
    };
    hideModal = () => {
        this.setState({ show: false });
    };

    setId = (id) => {
        this.modal.amount = id.amount
        this.modal.name = id.name
        this.modal.id = id.assesId
        this.modal.category = this.state.category

        this.setState({modal : this.modal})
        this.showModal()
    };
    renderBody = () => {
        const {List, isLoading} = this.state;
        if (isLoading) {
            return <p>Loading...</p>;
        } else {
            return  List.map(asset => {
                return <tr key={asset.assesId}>
                    <td style={{whiteSpace: 'nowrap'}}>{asset.name} </td>
                    <td>{asset.amount} </td>
                    <td>
                        <ButtonGroup>
                            <Button size="sm" color="primary" onClick={ () => { this.setState( {SelectedAsset : asset}); this.setId(asset) }} >Edit</Button>
                            <Button size="sm" color="danger"onClick={() => {this.setState( {SelectedAsset : asset}); this.remove(asset)}}>Delete</Button>
                        </ButtonGroup>
                    </td>
                </tr>
            })
        }
    }
    render() {
        return (
            <div>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" onClick={ () => {  this.setId({}) }}>Add New {this.props.Title}</Button>
                    </div>
                    <Modalpopup show={this.state.show} handleClose={this.hideModal}  Title ={'Edit Asset'} modal = {this.state.modal}  data = {this.state.SelectedAsset} fetchdata = {this.props.fetchdata}>
                    </Modalpopup>
                    <h5> {this.props.Title}</h5>
                    <Table  striped bordered hover size="sm" className="mt-4">
                        <tbody>
                        {this.renderBody()}
                        </tbody>
                    </Table>

                </Container>
            </div>

        )
    }

}
export default Liability;
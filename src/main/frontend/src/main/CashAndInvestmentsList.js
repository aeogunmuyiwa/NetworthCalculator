
import React, {Component, useState} from "react";
import {Button, ButtonGroup, Container, Table} from "reactstrap";
// import Modalpopup from '../Assets/AssetModal';

class CashAndInvestmentsList extends Component{
    modal = {
        name : '',
        amount: '',
        id : '',
        category : '',
    }


    constructor(props) {
        super(props);
        this.state = {List: this.props.list, SelectedAsset : {}, isLoading: true, show: false,modal : this.modal, category : this.props.category};
        this.remove = this.remove.bind(this);
    }



    componentDidMount() {
        this.setState({isLoading: false, List: this.props.list});
    }


     remove(asset) {

        console.log("endpoint",this.props.endpoint)
         fetch(`${this.props.endpoint}/${asset.assesId}/${this.state.category}`, {
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
            return;
        } else {
           return  List.map(asset => {
               return <tr  key={asset.assesId}  >
                   <td style={{whiteSpace: 'pre-wrap', color: 'black' ,wordWrap: 'break-word'}}>{asset.name} </td>
                   <td style={{whiteSpace: 'pre-wrap', color: 'black',wordWrap: 'break-word'}}>${asset.amount} </td>
                   <td>
                       <div className="float-right">
                           <Button size="sm" color="primary" onClick={ () => { this.setState( {SelectedAsset : asset}); this.setId(asset) }} >Edit</Button>{' '}
                           <Button size="sm" color="danger"onClick={() => {this.setState( {SelectedAsset : asset}); this.remove(asset)}}>Delete</Button>

                   </div>
                   </td>
               </tr>
            })
        }
    }
    render() {
        //New {this.props.Title}
        return (
            <div>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" onClick={ () => {  this.setId({}) }}>Add</Button>
                    </div>
                    <this.props.Modalpopup show={this.state.show} handleClose={this.hideModal}  Title ={'Edit Asset'} modal = {this.state.modal}  data = {this.state.SelectedAsset} fetchdata = {this.props.fetchdata}>
                    </this.props.Modalpopup>

                    <h5> {this.props.Title}</h5>

                    <Table  striped bordered hover size="sm" className="mt-4" style={{tableLayout : 'fixed', width: '100%'}}>
                        <tbody>
                        {this.renderBody()}
                        </tbody>
                    </Table>

                </Container>
            </div>

        )
    }

}
export default CashAndInvestmentsList;
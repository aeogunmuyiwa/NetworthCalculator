import React, {Component} from "react";
import {Button, Container, Table} from 'reactstrap';
import AddItem from "../Util/ModelPopup";
import  CashAndInvestmentsList from '../main/CashAndInvestmentsList'

/**
 * section to show asset name , amount , edit and delete functionality
 * */
const ListAssetApi = '/api'
class AssetsMain extends Component{
    constructor(props) {
        super(props);
        this.state = { show: false, List : [],isLoading: true}
    }
    Assetmodal = {
        name : '',
        amount: '',
        id : ''
    }
    componentDidMount() {
        this.setState({isLoading: true});
        fetch(ListAssetApi)
            .then(response => response.json())
            .then(data => this.setState({List: data, isLoading: false}))
    }


    hideModal = () => {
        this.setState({ show: false });
    };
    showModal = () => {
        this.setState({ show: true});
    };

     fetchdata = (prevProps, prevState) => {
        fetch(ListAssetApi)
            .then(response => response.json())
            .then(data => this.setState({List: data, isLoading: false}))

        this.componentDidMount()
    };

    render() {

        return (
            <div>
                <Container fluid>
                    <AddItem show={this.state.show} handleClose={this.hideModal} assetId = {''} modalId = {'New'} Title ={"Add a new asset"} modal = {this.Assetmodal} >
                    </AddItem>
                    <h5> Asset

                    </h5>
                    <Table  striped bordered hover size="sm" className="mt-4">
                        <tbody>
                        {this.renderBody()}
                        </tbody>
                    </Table>

                    <h5> Liabilities </h5>

                </Container>
            </div>

        )
    }
    renderBody = () => {
        const {List, isLoading} = this.state;
        if (isLoading) {
            return <p>Loading...</p>;
        }else{

            return List.map((list , index) => {
                return <tr key={list.assetTypeName}>

                    <td>
                        <CashAndInvestmentsList Title = {list.assetTypeName} list = {list.assetTypeList} category = {index} fetchdata = {this.fetchdata}/>
                    </td>
                </tr>


            })
        }

    }

}
export default AssetsMain;
package com.intuit.networthcalculator.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AssetTypeController {

    private  final AssetTypeService assetTypeService;

    @Autowired
    public AssetTypeController(AssetTypeService assetTypeService) {
        this.assetTypeService = assetTypeService;
    }

    @GetMapping("/asset")
    public List<AssistTypeList> getAssetTypes(){
        return assetTypeService.getAssetTypes();
    }

    @GetMapping("/liabilities")
    public  List<AssistTypeList> getLiabilityTypes(){
        return assetTypeService.getLiabilityTypes();
    }
    @GetMapping("/networth")
    public Networth getNetworth(){
        return assetTypeService.getNetworthData();
    }

    /**
     * Get ASSET BY ID AND CATEGORY
     * */
    @GetMapping("/asset/{Category}/{id}")
    ResponseEntity <AssetType> getGroup(@PathVariable int Category,  @PathVariable String id) {
      AssetType result = assetTypeService.findById(id,Category);
        if (result == null ) {
            return  ResponseEntity.notFound().build();
          //  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return ResponseEntity.ok().body(result);
        }
    }

//    @GetMapping("/addAsset/{name}/{amount}/{category}")
//    public List<AssistTypeList> createAsset(@PathVariable String name, @PathVariable int amount,@PathVariable int category )   {
//        AssetType newAsset = new AssetType();
//        newAsset.setName(name);
//        newAsset.setAmount((double) amount);
//        return assetTypeService.saveAsset(category,newAsset);
//    }

    /**
     * CREATE NEW ASSET
     * */
    @PostMapping("/asset/{category}")
    public List<AssistTypeList> newAsset( @PathVariable int category,@RequestBody AssetType newAsset) {
        AssetType Create_newAsset = new AssetType();
        Create_newAsset.setName(newAsset.getName());
        Create_newAsset.setAmount( newAsset.getAmount());
        return assetTypeService.saveAsset(category,Create_newAsset);

    }
    /**
     * UPDATE EXISTING ASSET
     * */
    @PutMapping("/asset/{id}/{category}")
    public ResponseEntity<AssetType> updateAsset( @PathVariable(value = "id") String id, @PathVariable(value = "category") int category, @RequestBody AssetType AssetDetails)  {
        AssetType result = assetTypeService.findById(id, category);
        if (result == null ) {
            return  ResponseEntity.notFound().build();
            //  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            result.setName(AssetDetails.getName());
            result.setAmount(AssetDetails.getAmount());
            return ResponseEntity.ok().body(result);
        }
    }


    /**
     * DELETE ASSET
     * */
    @DeleteMapping("/asset/{id}/{category}")
    public ResponseEntity<?> deleteGroup(@PathVariable String id , @PathVariable int category) {
        assetTypeService.deleteAsset(id, category);
        return ResponseEntity.ok().build();
    }
    /**
     * Get Liability BY ID AND CATEGORY
     * */
    @GetMapping("/liabilities/{Category}/{id}")
    ResponseEntity <AssetType> getLiability(@PathVariable int Category, @PathVariable String id) {
        AssetType result = assetTypeService.findByLiabilityId(id,Category);
        if (result == null ) {
            return  ResponseEntity.notFound().build();
            //  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return ResponseEntity.ok().body(result);
        }
    }

    /**
     * CREATE NEW LIABILITY
     * */
    @PostMapping("/liabilities/{category}")
    public List<AssistTypeList> newLiability(@PathVariable int category, @RequestBody AssetType newAsset) {
        AssetType Create_newAsset = new AssetType();
        Create_newAsset.setName(newAsset.getName());
        Create_newAsset.setAmount(newAsset.getAmount());
        return assetTypeService.saveLiability(category,Create_newAsset);

    }

    /**
     * UPDATE EXISTING LIABILITY
     * */
    @PutMapping("/liabilities/{id}/{category}")
    public ResponseEntity<AssetType> updateLiability( @PathVariable(value = "id") String id, @PathVariable(value = "category") int category, @RequestBody AssetType AssetDetails)  {
        AssetType result = assetTypeService.findByLiabilityId(id, category);
        if (result == null ) {
            return  ResponseEntity.notFound().build();
            //  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            result.setName(AssetDetails.getName());
            result.setAmount(AssetDetails.getAmount());
            return ResponseEntity.ok().body(result);
        }
    }

    /**
     * DELETE LIABILITY
     * */
    @DeleteMapping("/liabilities/{id}/{category}")
    public ResponseEntity<?> deleteLiability(@PathVariable String id , @PathVariable int category) {
        assetTypeService.deleteLiability(id, category);
        return ResponseEntity.ok().build();
    }




}

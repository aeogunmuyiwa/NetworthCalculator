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

    @GetMapping
    public List<AssistTypeList> getAssetTypes(){
        return assetTypeService.getAssetTypes();
    }

    @GetMapping("/assets/{Category}/{id}")
    ResponseEntity <AssetType> getGroup(@PathVariable int Category,  @PathVariable String id) {
      AssetType result = assetTypeService.findById(id,Category);
        if (result == null ) {
            return  ResponseEntity.notFound().build();
          //  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            return ResponseEntity.ok().body(result);
        }
    }

    @GetMapping("/addAsset/{name}/{amount}/{category}")
    public List<AssistTypeList> createAsset(@PathVariable String name, @PathVariable int amount,@PathVariable int category )   {
        AssetType newAsset = new AssetType();
        newAsset.setName(name);
        newAsset.setAmount((double) amount);
        return assetTypeService.saveAsset(category,newAsset);
    }


    @PostMapping("/asset/{category}")
    public List<AssistTypeList> newAsset( @PathVariable int category,@RequestBody AssetType newAsset) {
        AssetType Create_newAsset = new AssetType();
        Create_newAsset.setName(newAsset.getName());
        Create_newAsset.setAmount((double) newAsset.getAmount());
        return assetTypeService.saveAsset(category,Create_newAsset);

    }
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

//    @PostMapping("/addAsset/{id}")
//    ResponseEntity<AssetType> updateAsset(@RequestBody AssetType asset )throws URISyntaxException {
//        AssetType result =  assetTypeService.saveAsset(asset);
//        return ResponseEntity.ok().body(result);
//    }

    @DeleteMapping("/asset/{id}/{category}")
    public ResponseEntity<?> deleteGroup(@PathVariable String id , @PathVariable int category) {
        assetTypeService.deleteAsset(id, category);
        return ResponseEntity.ok().build();
    }

}

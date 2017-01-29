'use strict';

var express = require('express');
var path = require('path');
var bodyParser = require('body-parser');
var fs = require('fs');
var app = express();
var mongoose = require('mongoose');
var multer = require('multer');


var storage = multer.diskStorage({
    destination: function (req, file, cb) {
        cb(null, 'uploads/');
    },
    filename: function (req, file, cb) {
        cb(null, Date.now() + '-' + file.originalname);
    }
});

var upload = multer({ storage: storage });



mongoose.connect('mongodb://localhost:27017/test2');

var db = mongoose.connection;
mongoose.Promise = global.Promise;


// Table Schema start
var userSchema = mongoose.Schema({
    fullname: String,
    email: String,
    username: String,
    password: String,
    dateOfBirth : String,
    education: String,
    selfDescription: String,
    hobbies: String

});



var buyingSchema = mongoose.Schema({
    category: String,
    imgUrl: String,
    desc: String,
    amount : Number,
    username: String,
    status: String,
    title : String
});



var rentingSchema = mongoose.Schema({
    category: String,
    imageUrl: String,
    desc: String,
    amount: Number,
    username : String,
    buyerId: String,
    status: String,
    startDate: String,
    endDate: String,
    title : String
});


var biddingSchema = mongoose.Schema({
    buyId: String,
    biddingUserId: String,
    bidAmount: Number,
    status: String

});
// Table Schema end


var User = mongoose.model('User', userSchema);
var BuyingTable = mongoose.model('BuyingTable', buyingSchema);
var RentingTable = mongoose.model('RentingTable', rentingSchema);
var BiddingTable = mongoose.model('BiddingTable', biddingSchema);


app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: false }));


db.on('error', console.error.bind(console, 'connection error:'));

db.once('open', function () {
    console.log('Connected to MongoDB asdsad');

    app.post('/login', function (req, res) {
        console.log('received at server 123', req.body);
        // email:req.body.email, password:req.body.password

        User.findOne({ username: req.body.username, password: req.body.password }, function (err, fetchedUsers) {
            console.log("first printing 1");
            if (err) {

                console.error(err);
                res.status(550).json({ message: 'Error Occurred' });

            } else if (!fetchedUsers) {

                console.error(err);
                res.status(551).json({ message: 'No Users found' });

            } else {
                console.log("before printing 2 >>", fetchedUsers);
                res.json(fetchedUsers);
            }

        })

    });



    app.post('/user-sign-up', function (req, res) {
        console.log('Request received for user sign up', req.body);
        User.findOne({ username: req.body.username }, function (err, fetchedUser) {
            if (fetchedUser) {
                res.json({ errorMessage: 'User Already Exist', errorCode: 1001 });
            } else {
                var newUser = new User(req.body);
                var savedDoc;
                newUser.save().then(doc => {
                    savedDoc = doc;
                    console.log('savedDoc :', savedDoc);
                    res.json(savedDoc);
                });
            }
        });

    });

    app.post('/user-login', function (req, res) {
        User.findOne({ username: req.body.username, password: req.body.password },
            function (err, fetchedUsers) {
                if (err) {
                    res.json({ errorMessage: 'Oops, we have an error', errorCode: 1000 });

                } else if (!fetchedUsers) {

                    res.json({ errorMessage: 'Incorrect Username or password', errorCode: 1002 });
                } else {
                    res.json(fetchedUsers);
                }

            })

    });

    app.get("/getUserDetails/:username", function(req,res){
        var username = req.params.username;
        console.log(username);
        User.findOne({username : username}, function(err, fetchedUser){
            res.json(fetchedUser);
        });
    });

    app.post("/item/upload", upload.array("uploads", 12), function (req, res) {
        console.log('received files files obj', req.files[0]);
        console.log('received files files obj', req.body);
        var type = req.body.type;
        
        if (type != "Rent") {
            console.log('saving in buying table');
            var product = new BuyingTable();
            product.category = req.body.category;
            product.imgUrl = req.files[0].path;
            product.desc = req.body.desc;
            product.amount = req.body.amount;
            product.title = req.body.title;
            product.username = req.body.username;
            product.status = 'AVAILABLE';
            product.save();
            req.body.file = req.files[0];
            req.body.statusCode = 200;
            req.body.statusMessage = "Success";
        } else {
            console.log('saving in rent table');
            var product = new RentingTable();
            product.category = req.body.category;
            product.imageUrl = req.files[0].path;
            product.desc = req.body.desc;
            product.amount = req.body.amount;
            product.username = req.body.username;
            product.status = 'AVAILABLE';
            //product.startDate = req.body.startDate;
           // product.endDate = req.body.endDate;
            product.title = req.body.title;
            product.save();

        }
        res.send(req.body);
    });

    app.get('/findAllItemsSold/:user', function (req, res) {
        console.log('received request');
        var user = req.params.user;

        BuyingTable.find({ username: user }).exec().then(result => {
            res.send(result);
        });
    });

    app.get('/findAllItemsForSale', function (req, res) {
        BuyingTable.find({ status: "AVAILABLE" }, function (err, data) {
            res.json(data);
        });
    });

    app.get('/findAllItemsForRent', function (req, res) {
        RentingTable.find({ status: "AVAILABLE" }, function (err, data) {
            res.json(data);
        });
    });



    app.get('/getHighestPrice/:id', function (req, res) {
        var buyId = req.params.id;
        BiddingTable.findOne({ buyId: buyId })
            .sort('-bidAmount')
            .exec()
            .then(data => {
                if (data) {
                    res.json({ highestAmount : data.bidAmount });
                } else {
                    res.json({ highestAmount : 0 });
                }

            });
    });

    app.put('/finalizeBid/:prodId/:sellerId/:buyerId', function (req, res) {
        var prodId = req.params.prodId;
        var sellerId = req.params.sellerId;
        var buyerId = req.params.buyerId;
        BuyingTable.findOne({ _id: bidId }, function (err, doc) {
            doc.status = 'SOLD';
            doc.save();
        });
        BiddingTable.findOne({ buyId: prodId, biddingUserId: buyerId }, function (err, data) {

            data.status = 'SOLD';
            data.save();
        });
        res.json({ statusCode: 200, statusMessage: 'Sold Successfully' });
    });


    app.get('/findAllItemsPurchased/:id', function (req, res) {
        var buyerId = req.params.id;
        BiddingTable.find({ biddingUserId: buyerId, status: 'SOLD' }, function (err, data) {
            res.json(data);
        });
    });

    
    app.get('/findAllItemsRented/:id', function (req, res) {
        var buyerId = req.params.id;
        RentingTable.find({ buyerId: buyerId, status: 'SOLD' }, function (err, data) {
            res.json(data);
        });
    });

    app.post("/placeorder", function (req, res) {
        var buyId = req.body.buyId;
        var biddingUserId = req.body.biddingUserId;
        console.log('received files for bidding', req.body);
        BiddingTable.findOne({ buyId: buyId, biddingUserId: biddingUserId }, function (err, data) {

            if (data) {
                order.bidAmount = req.body.bidAmount;
                order.save();
            } else {
                var order = new BiddingTable();
                order.buyId = buyId;
                order.biddingUserId = biddingUserId;
                order.bidAmount = req.body.bidAmount;
                order.save();
            }
        });
        req.body.statusCode = 200;
        req.body.statusMessage = "Success";
        res.send(req.body);
    });

    app.post("/placerentorder", function (req, res) {
        var buyId = req.body.buyId;
        var buyerId = req.body.buyerId;

        RentingTable.findOne({ _id: buyId }, function (err, data) {
            if (data) {
                data.status = "SOLD";
                data.buyerId = buyerId;
                data.save();
            } else {
                console.log('Error while saving order for renting an item');
            }
            res.send(req.body);
        });
    });





});



app.post("/api/photo", upload.array("uploads", 12), function (req, res) {
    console.log('received files files obj', req.files[0]);
    res.send(req.files);
});




app.use(function (req, res, next) {
    res.header("Access-Control-Allow-Origin", "*");
    res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
    next();
});

app.get('/node_modules*', function (req, res, next) {
    res.sendFile(path.join(__dirname + req.url));

});
app.get('/app*', function (req, res, next) {
    res.sendFile(path.join(__dirname + req.url));

});

app.get('/public*', function (req, res, next) {
    res.sendFile(path.join(__dirname + req.url));

});



app.get('/fonts*', function (req, res, next) {
    res.sendFile(path.join(__dirname + req.url));

});

app.get('/test', function (req, res) {
    console.log('getting data from server');
    res.json({ result: 200 });

});


//app.use('/', express.static(__dirname+'/app'));

app.get('/marketplace/', function (req, res) {
    res.sendFile(path.join(__dirname + '/index.html'));
});

app.get('/uploads*', function (req, res, next) {
    res.sendFile(path.join(__dirname + req.url));

});


var ip = process.env.OPENSHIFT_NODEJS_IP || '127.0.0.1';
var port = process.env.OPENSHIFT_NODEJS_PORT || 3000;

app.listen(port, ip);




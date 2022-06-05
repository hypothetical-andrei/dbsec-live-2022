db.stuff.insert({ a: 1 })

db.stuff.insert({ something: "some value" })

db.stuff.find({ a: 1 })

db.stuff.insert({
  name: "Jim",
  age: 44,
  address: {
    street: 'somestreet',
    number: 5 
  }
})

db.stuff.find({
  name: {
    $exists: true
  }
})

db.stuff.find({
  name: {
    $exists: true
  },
  age: {
    $gt: 40
  }
}, {
  name: 1,
  age: 1,
  _id: 0
}).map(e => e.age * 2)

db.stuff.remove({
  a: 1
})

db.stuff.updateMany({
  name: 'Jim'
}, {
  $set: {
    age: 45
  }
})

db.stuff.find({
  'address.number': 5
})

var items = [0,1,2,3,4,5,6,7,8,9].map(function(e){
	return {
		name : 'student' + e,
		studentNo : e
	}
})

items.forEach(e => {
  db.students.insert(e)
})

var e = {
	name : 'john',
	position : 'programmer',
	salary : 1000
}

db.employees.insert(e)

e = {
	name : 'jim',
	position : 'programmer',
	salary : 1500
}

db.employees.insert(e)

e = {
	name : 'jane',
	position : 'accountant',
	salary : 1500
}

db.employees.insert(e)

db.employees.find({
  $or: [{
    position: 'programmer'
  }, {
    salary: {
      $gt: 1200
    }
  }]
})

db.employees.find({
  position: {
    $in: ['accountant', 'programmer']
  }
})

var items = [10,11,12,13,14,15,16,17,18,19].map(function(e){
	return {
		name : 'student' + e,
		studentNo : e
	}
})

for (var i = 0; i < items.length; i++){
	db.students.insert(items[i])
}

e = {
	name : 'anne',
	position : 'accountant',
	salary : 1000
}

db.employees.insert(e)

db.employees.updateMany({},{
  $inc: { salary: 50 }
})

db.employees.remove({})

var items = [0,1,2,3,4,5,6,7,8,9].map(function(e){
	return {
		name : 'employee' + e,
		salary : e * 1000,
		job : (e % 2 ? 'programmer' : 'accountant')
	}
})

for (var i = 0; i < items.length; i++) {
	db.employees.insert(items[i])
}

db.employees.aggregate([ {
  $match: {
    job: 'programmer'
  }
}, {
  $project: {
    _id: 0,
    salary: 1,
    job: 1
  }
}, {
  $group: {
    _id: '$job',
    avg_salary: {
      $avg: '$salary'
    },
    count: {
      $sum: 1
    }
  }
}])

db.employees.aggregate([  {
  $project: {
    _id: 0,
    salary: 1,
    job: 1
  }
}, {
  $group: {
    _id: '$job',
    avg_salary: {
      $avg: '$salary'
    },
    count: {
      $sum: 1
    }
  }
}])

var items  = [0,1,2,3,4,5,6,7,8,9].map(function(e){
	return {
		name : 'student' + 0,
		student_number : '000' + e
	}
})

for (var i = 0; i < items.length; i++){
	db.students.insert(items[i])
}

db.students.find().map(function(e){
	return e._id
})

var items = [
	ObjectId("629b2cdd99da22ee40b5f5cd"),
	ObjectId("629b2cdd99da22ee40b5f5ce"),
	ObjectId("629b2cdd99da22ee40b5f5cf"),
	ObjectId("629b2cdd99da22ee40b5f5d0"),
	ObjectId("629b2cdd99da22ee40b5f5d1"),
	ObjectId("629b2cdd99da22ee40b5f5d2"),
	ObjectId("629b2cdd99da22ee40b5f5d3"),
	ObjectId("629b2cdd99da22ee40b5f5d4"),
	ObjectId("629b2cdd99da22ee40b5f5d5"),
	ObjectId("629b2cdd99da22ee40b5f5d6"),
	ObjectId("629b2eaf99da22ee40b5f5da"),
	ObjectId("629b2eaf99da22ee40b5f5db"),
	ObjectId("629b2eaf99da22ee40b5f5dc"),
	ObjectId("629b2eaf99da22ee40b5f5dd"),
	ObjectId("629b2eaf99da22ee40b5f5de"),
	ObjectId("629b2eaf99da22ee40b5f5df"),
	ObjectId("629b2eaf99da22ee40b5f5e0"),
	ObjectId("629b2eaf99da22ee40b5f5e1"),
	ObjectId("629b2eaf99da22ee40b5f5e2"),
	ObjectId("629b2eaf99da22ee40b5f5e3"),
	ObjectId("629b342de35f9fef09d19eea"),
	ObjectId("629b342de35f9fef09d19eeb"),
	ObjectId("629b342de35f9fef09d19eec"),
	ObjectId("629b342de35f9fef09d19eed"),
	ObjectId("629b342de35f9fef09d19eee"),
	ObjectId("629b342de35f9fef09d19eef"),
	ObjectId("629b342de35f9fef09d19ef0"),
	ObjectId("629b342de35f9fef09d19ef1"),
	ObjectId("629b342de35f9fef09d19ef2"),
	ObjectId("629b342de35f9fef09d19ef3")
].map(function (e, i){
	return {
		username : 'user' + i,
		type : (i % 2 ? 'limited' : 'full'),
		details : e
	}
})

for (var i = 0; i < items.length; i++){
	db.users.insert(items[i])
}

db.users.aggregate([{
  $lookup: {
    from: 'students',
    localField: 'details',
    foreignField: '_id',
    as: 'user_details'
  }
}, {
  $unwind: '$user_details'
}, {
  $project: {
    _id: 0,
    username: 1,
    type: 1,
    name: '$user_details.name' 
  }
}])

var grademap = function() { 
  emit(this.name, this.grade)
}

var gradereduce = function(keyName, values) { 
  return Array.avg(values)
}

db.places.find({
	loc : {
		$near : {
			$geometry : {
				type : "Point",
				coordinates : [-73.96, 40.78]
			},
			$maxDistance : 5000
		}
	}
})
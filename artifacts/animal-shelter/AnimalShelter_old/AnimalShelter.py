from pymongo import MongoClient
from bson.objectid import ObjectId

class AnimalShelter(object):
	""" CRUD operations for Animal collection in MongoDB """
	def __init__(self, username, password):
		# Initializing the MongoClient. This helps to
		# access the MongoDB databases and collections.
		HOST = 'nv-desktop-services.apporto.com'
		PORT = 33831
		DB = 'AAC'
		COL = 'animals'
		#
		# Initialize Connection
		#
		self.client = MongoClient('mongodb://%s:%s@%s:%d' % (username,password,HOST,PORT))
		self.database = self.client['%s' % (DB)]
		self.collection = self.database['%s' % (COL)]

	# The C in CRUD - Create data
	def create(self, data):
		if data is not None:
			self.collection.insert_one(data) # Insert a single entry of the given data
			return True
		else:
			raise Exception("Nothing to save, because data parameter is empty")
			return False

    # R - Read data
	def read(self, query):
		if query is not None:
			return list(self.collection.find(query)) # Search for the number of data containing the query parameter
		else:
			raise Exception("Failed to read")

    # U - Update data
	def update(self, query, updates):
		if query is not None and updates is not None:
			result = self.collection.update_many(query, {"$set": updates}) # Update all entries of the first value to contain the second value
			return result.modified_count
        
    # D - Delete data
	def delete(self, query):
		if query is not None:
			result = self.collection.delete_many(query) # Delete all entries containing the query value
			return result.deleted_count
            